package park.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import park.exception.ParkSpotBeOccupyException;
import park.exception.ParkSpotNumOutOfBoundsException;
import park.exception.ParkWithoutFreeSpotException;
import park.mapper.ParkSpotMapper;
import park.pojo.Park;
import park.pojo.ParkSpot;
import park.utils.Page4DataTable;
import park.utils.UUIDUtils;

@Service
public class ParkSpotService {
	@Autowired
	ParkSpotMapper parkSpotMapper;
	@Autowired
	private ParkService parkService;

	/**
	 * 根据停车场id 获取该停车场一页的车位信息
	 * 
	 * @author whp
	 * @param allData
	 * @param parkId
	 */
	public void getPageParkSpot(Page4DataTable<ParkSpot> allData, String parkId) {
		try {
			int count = parkSpotMapper.getParkSpotNumByParkId(parkId);
			System.out.println(count);
			allData.setiTotalRecords(count);
			allData.setiTotalDisplayRecords(count);
			List<ParkSpot> data = parkSpotMapper.getPageParkSpotsByParkId(allData.getiDisplayStart(),
					allData.getiDisplayStart() + allData.getiDisplayLength(), parkId);
			allData.setAaData(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据停车场id 获取该停车场尚未被添加的车位数
	 * 
	 * @author whp
	 * @param parkId
	 * @param parkSpotNum
	 * @return 停车场还可以添加的车位数
	 * @throws ParkSpotNumOutOfBoundsException
	 */
	public int getRemanentSpotNum(String parkId, String parkSpotNum) throws ParkSpotNumOutOfBoundsException {
		int parkSpotNum1 = Integer.parseInt(parkSpotNum);
		/** 已经添加的车位数 */
		int spotNum = parkSpotMapper.getParkSpotNumByParkId(parkId);
		if (spotNum > parkSpotNum1) {
			throw new ParkSpotNumOutOfBoundsException("当前停车场车位表中的车位数比设定值大");
		}
		return parkSpotNum1 - spotNum;
	}

	/**
	 * 批量添加车位
	 * 
	 * @author whp
	 * @param parkId
	 * @param spotPlace
	 * @param spotNum
	 * @param spotType
	 */
	public void setParkSpotNum(String parkId, String spotPlace, String spotAddNum1, String spotType) {
		int spotAddNum = Integer.parseInt(spotAddNum1);
		List<ParkSpot> list = new ArrayList<ParkSpot>();// 存放要添加的车位
		// 获取数据库中当前车位地点的编号最大值
		int maxSpotNum = (parkSpotMapper.getMaxSpotNum(spotPlace, parkId) == null) ? 0
				: parkSpotMapper.getMaxSpotNum(spotPlace, parkId);
		// 从编号最大值开始往后进行编号
		for (int i = 1; i <= spotAddNum; i++) {
			int spotNum = maxSpotNum + i;// 车位编号
			String parkSpotId = UUIDUtils.getUUID();
			ParkSpot ps = new ParkSpot(parkSpotId, parkId, spotPlace, spotNum + "", spotType);
			list.add(ps);
		}
		// 将list中的数据添加到表中
		parkSpotMapper.insertSpotByBatch(list);
	}

	/**
	 * 根据车位id，获取车位所有信息
	 * 
	 * @author whp
	 * @param parkSpotId
	 * @return
	 */
	public ParkSpot getPSById(String parkSpotId) {
		ParkSpot ps = parkSpotMapper.getParkSpotById(parkSpotId);
		return ps;
	}

	/**
	 * 根据车位id，更新车位信息
	 * 
	 * @author whp
	 * @param parkSpot
	 */
	public void updatePS(ParkSpot parkSpot) {
		parkSpotMapper.updateParkSpot(parkSpot);
	}

	/**
	 * 删除车位
	 * 
	 * @author whp
	 * @param parkSpotId
	 */
	public void deletePSById(String parkSpotId) {
		parkSpotMapper.deleteParkSpotById(parkSpotId);
	}

	/**
	 * 获取停车场所有的空闲车位
	 * 
	 * @author whp
	 * @param parkId
	 * @return
	 * @throws ParkWithoutFreeSpotException
	 */
	public List<ParkSpot> getFreeParkSpots(Park park) throws ParkWithoutFreeSpotException {
		List<ParkSpot> parkSpots = parkSpotMapper.getParkSpotByParkId(park.getParkId());
		if (parkSpots == null || parkSpots.size() == 0) {
			throw new ParkWithoutFreeSpotException("停车场没有空闲车位了");
		}
		return parkSpots;
	}
	/**
	 * 进入停车场后业务逻辑，更新车位的信息
	 * @author whp
	 * @param parkSpot
	 * @param carId
	 * @throws ParkSpotBeOccupyException
	 */
	public void enterParkUpdateSpot(ParkSpot parkSpot, String carId) throws ParkSpotBeOccupyException {
		// 2.1 车位被占用，更新
		if (parkSpot.getSpotState() == 1) {
			throw new ParkSpotBeOccupyException("当前车位已经被占用");
		}
		parkSpot.setSpotState(1);
		// 2.2 车位车辆信息更新
		parkSpot.setCarId(carId);
		updatePS(parkSpot);
	}
	/**
	 * 退出停车场后业务逻辑，更新车位信息
	 * @author whp
	 * @param parkSpot
	 */
	public void outParkUpdateSpot(ParkSpot parkSpot) {
		if(parkSpot.getSpotState()==1) {
			parkSpot.setSpotState(0);
			parkSpot.setCarId(null);
			parkSpotMapper.updateParkSpot(parkSpot);
		}
	}


}
