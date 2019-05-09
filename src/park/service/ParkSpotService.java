package park.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import park.mapper.ParkSpotMapper;
import park.pojo.ParkSpot;
import park.utils.Page4DataTable;
import park.utils.UUIDUtils;

@Service
public class ParkSpotService {
	 @Autowired 
	 ParkSpotMapper parkSpotMapper;
	 /**
	  * 根据停车场id
	  * 获取该停车场一页的车位信息
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
					allData.getiDisplayStart()+allData.getiDisplayLength(),
					parkId);
			allData.setAaData(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据停车场id
	 * 获取该停车场尚未被添加的车位数
	 * 
	 * @author whp
	 * @param parkId
	 * @param parkSpotNum 
	 * @return 停车场还可以添加的车位数
	 */
	public int getRemanentSpotNum(String parkId, String parkSpotNum) {
		int parkSpotNum1 = Integer.parseInt(parkSpotNum);
		/** 已经添加的车位数*/
		int spotNum = parkSpotMapper.getParkSpotNumByParkId(parkId);
		if(spotNum>parkSpotNum1) {
			try {
				throw new  Exception("当前停车场车位表中的车位数比设定值大");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return parkSpotNum1-spotNum;
	}
	/**
	 * 批量添加车位
	 * @author whp
	 * @param parkId
	 * @param spotPlace
	 * @param spotNum
	 * @param spotType
	 */
	public void setParkSpotNum(String parkId, String spotPlace, String spotAddNum1, String spotType) {
		int spotAddNum = Integer.parseInt(spotAddNum1);
		List<ParkSpot> list = new ArrayList<ParkSpot>();//存放要添加的车位
		//获取数据库中当前车位地点的编号最大值
		int maxSpotNum = (parkSpotMapper.getMaxSpotNum(spotPlace,parkId)==null)?0:parkSpotMapper.getMaxSpotNum(spotPlace,parkId);
		System.out.println(maxSpotNum);
		//从编号最大值开始往后进行编号
		for(int i=1; i<=spotAddNum; i++) {
			int spotNum=maxSpotNum+i;//车位编号
			String parkSpotId = UUIDUtils.getUUID();
			ParkSpot ps = new ParkSpot(parkSpotId,parkId,spotPlace,spotNum+"",spotType);
			list.add(ps);
		}
		// 将list中的数据添加到表中
		System.out.println(list);
		parkSpotMapper.insertSpotByBatch(list);
	}
	public ParkSpot getPSById(String parkSpotId) {
		ParkSpot ps = parkSpotMapper.getParkSpotById(parkSpotId);
		return ps;
	}

	 
	 
}
