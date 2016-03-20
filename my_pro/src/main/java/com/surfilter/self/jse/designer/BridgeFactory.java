package com.surfilter.self.jse.designer;

/**
 * 
* @ClassName: BridgeFactory  
* @Description: 桥接模式
* @author quanli 
* @date 2016年3月19日 下午12:00:57  
*
 */
public class BridgeFactory {

	//桥接模式的意义在于将表现与实现进行区分
	public static void main(String[] args) {
		
		BridgeFactory bf = new BridgeFactory();
		
		DBDriver dbDriver =  bf.new MySQLDriver();
		DriverManager dm = bf.new DriverManager(dbDriver);
		dm.useDb();
		
		dbDriver = bf.new OracleDriver();
		dm = bf.new DriverManager(dbDriver);
		dm.useDb();
	}
	
	//数据库驱动
	interface DBDriver{
		public void driverDb();
	}
	
	//mysql
	class MySQLDriver implements DBDriver{
		@Override
		public void driverDb() {
			System.out.println("加载mysql驱动");
		}
	}
	//Oracle
	class OracleDriver implements DBDriver{
		@Override
		public void driverDb() {
			System.out.println("加载oracle驱动");
		}
	}
	
	//数据库连接操作
	class DriverManager{
		private DBDriver dbDriver;

		public DriverManager(DBDriver dbDriver) {
			super();
			this.dbDriver = dbDriver;
		}

		public DBDriver getDbDriver() {
			return dbDriver;
		}

		public void setDbDriver(DBDriver dbDriver) {
			this.dbDriver = dbDriver;
		}
		
		public void useDb(){
			dbDriver.driverDb();
		}
	}
	
	
}
