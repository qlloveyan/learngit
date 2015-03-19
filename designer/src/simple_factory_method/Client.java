package simple_factory_method;

public class Client {

	public static void main(String[] args) {
		Factory factory = new Factory();
		ProductA pa = (ProductA) factory.createProduct("simple_factory_method.ProductA");
		pa.say();
	}
}
