package com.bool.carshare.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1206933084685838683L;
	
	//主键id
	private Integer manageId;
	//用户名
	private String manageName;
	//用户全名
	private String manageAllName;
	//部门
	private String department;
	//电话
	private String phone;
	//邮箱
	private String mailbox;
	//用户状态 0：未启用   1：启用
	private String userStatus;
	//用户级别
	private String userLevel;
	//创建时间
	private String createTime;
	//状态时间
	private String statusTime;
	//登陆名
	private String manageLogin;
	//密码
	private String password;
	//角色名称
	private String roleName;
	//车辆监控
	private int car_1;
	//车辆管理
	private int car_2;
	//车辆维护
	private int car_3;
	//终端管理
	private int car_4;
	//网点监控
	private int dot_1;
	//网点管理
	private int dot_2;
	//会员列表
	private int member_1;
	//会员审核
	private int member_2;
	//订单查询
	private int order_1;
	//节点列表
	private List<NodeInfo> nodeList;
	//节点
	private Map<String, Integer> node;
	//照片
	private String photo;
	
	public Integer getManageId() {
		return manageId;
	}
	public void setManageId(Integer manageId) {
		this.manageId = manageId;
	}
	public String getManageName() {
		return manageName;
	}
	public void setManageName(String manageName) {
		this.manageName = manageName;
	}
	public String getManageAllName() {
		return manageAllName;
	}
	public void setManageAllName(String manageAllName) {
		this.manageAllName = manageAllName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMailbox() {
		return mailbox;
	}
	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStatusTime() {
		return statusTime;
	}
	public void setStatusTime(String statusTime) {
		this.statusTime = statusTime;
	}
	public String getManageLogin() {
		return manageLogin;
	}
	public void setManageLogin(String manageLogin) {
		this.manageLogin = manageLogin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getCar_1() {
		return car_1;
	}
	public void setCar_1(int car_1) {
		this.car_1 = car_1;
	}
	public int getCar_2() {
		return car_2;
	}
	public void setCar_2(int car_2) {
		this.car_2 = car_2;
	}
	public int getCar_3() {
		return car_3;
	}
	public void setCar_3(int car_3) {
		this.car_3 = car_3;
	}
	public int getCar_4() {
		return car_4;
	}
	public void setCar_4(int car_4) {
		this.car_4 = car_4;
	}
	public int getDot_1() {
		return dot_1;
	}
	public void setDot_1(int dot_1) {
		this.dot_1 = dot_1;
	}
	public int getDot_2() {
		return dot_2;
	}
	public void setDot_2(int dot_2) {
		this.dot_2 = dot_2;
	}
	public int getMember_1() {
		return member_1;
	}
	public void setMember_1(int member_1) {
		this.member_1 = member_1;
	}
	public int getMember_2() {
		return member_2;
	}
	public void setMember_2(int member_2) {
		this.member_2 = member_2;
	}
	public int getOrder_1() {
		return order_1;
	}
	public void setOrder_1(int order_1) {
		this.order_1 = order_1;
	}
	// TODO 由于前台页面上的节点不是后台系统提供的，因此这里显示地声明了节点的ID与名称，有待后期前台页面节点与后台关联
	public List<NodeInfo> generateNodeList() {
		NodeInfo car1Node = new NodeInfo();
		car1Node.setId(2);
		car1Node.setName("车辆监控");
		car1Node.setOperate_type(this.car_1);
		
		NodeInfo car2Node = new NodeInfo();
		car2Node.setId(3);
		car2Node.setName("车辆管理");
		car2Node.setOperate_type(this.car_2);
		
		NodeInfo car3Node = new NodeInfo();
		car3Node.setId(4);
		car3Node.setName("车辆维护");
		car3Node.setOperate_type(this.car_3);
		
		NodeInfo car4Node = new NodeInfo();
		car4Node.setId(5);
		car4Node.setName("终端管理");
		car4Node.setOperate_type(this.car_4);
		
		NodeInfo dot1Node = new NodeInfo();
		dot1Node.setId(7);
		dot1Node.setName("网点监控");
		dot1Node.setOperate_type(this.dot_1);
		
		NodeInfo dot2Node = new NodeInfo();
		dot2Node.setId(8);
		dot2Node.setName("网点管理");
		dot2Node.setOperate_type(this.dot_2);
		
		NodeInfo member1Node = new NodeInfo();
		member1Node.setId(10);
		member1Node.setName("会员列表");
		member1Node.setOperate_type(this.member_1);
		
		NodeInfo member2Node = new NodeInfo();
		member2Node.setId(11);
		member2Node.setName("会员审核");
		member2Node.setOperate_type(this.member_2);
		
		NodeInfo order1Node = new NodeInfo();
		order1Node.setId(13);
		order1Node.setName("订单查询");
		order1Node.setOperate_type(this.order_1);
		
		List<NodeInfo> nodeList = new ArrayList<NodeInfo>();
		nodeList.add(car1Node);
		nodeList.add(car2Node);
		nodeList.add(car3Node);
		nodeList.add(car4Node);
		nodeList.add(dot1Node);
		nodeList.add(dot2Node);
		nodeList.add(member1Node);
		nodeList.add(member2Node);
		nodeList.add(order1Node);
		
		return nodeList;
	}
	public List<NodeInfo> getNodeList() {
		return this.nodeList;
	}
	public void setNodeList(List<NodeInfo> nodeList) {
		this.nodeList = nodeList;
	}
	public Map<String, Integer> generateNode() {
		if(this.nodeList != null) {
			Map<String, Integer> node = new HashMap<String, Integer>();
			
			for(int n=0; n<this.nodeList.size(); n++) {
				NodeInfo nodeInfo = this.nodeList.get(n);
				
				node.put(nodeInfo.getName(), nodeInfo.getOperate_type());
			}
			
			this.car_1 = node.get("车辆监控")==null?0:node.get("车辆监控");
			this.car_2 = node.get("车辆管理")==null?0:node.get("车辆管理");
			this.car_3 = node.get("车辆维护")==null?0:node.get("车辆维护");
			this.car_4 = node.get("终端管理")==null?0:node.get("终端管理");
			this.dot_1 = node.get("网点监控")==null?0:node.get("网点监控");
			this.dot_2 = node.get("网点管理")==null?0:node.get("网点管理");
			this.member_1 = node.get("会员列表")==null?0:node.get("会员列表");
			this.member_2 = node.get("会员审核")==null?0:node.get("会员审核");
			this.order_1 = node.get("订单查询")==null?0:node.get("订单查询");
			
			return node;
		}
		
		return null;
	}
	public Map<String, Integer> getNode() {
		return this.node;
	}
	public void setNode(Map<String, Integer> node) {
		this.node = node;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ManageInfo(Integer manageId, String manageName, String manageAllName, String department, String phone,
			String mailbox, String userStatus, String userLevel, String createTime, String statusTime,
			String manageLogin, String password, String roleName) {
		super();
		this.manageId = manageId;
		this.manageName = manageName;
		this.manageAllName = manageAllName;
		this.department = department;
		this.phone = phone;
		this.mailbox = mailbox;
		this.userStatus = userStatus;
		this.userLevel = userLevel;
		this.createTime = createTime;
		this.statusTime = statusTime;
		this.manageLogin = manageLogin;
		this.password = password;
		this.roleName = roleName;
	}
	public ManageInfo() {
		super();
	}
	
	

}
