package simple_factory_method;

public class Factory {

	public Object createProduct(String className){
		Object result = null;
		try {
			Class obj = Class.forName(className);
			result =  obj.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
