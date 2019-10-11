package src;

public class Main
{
    public static void main(String[] args)
    {
        AbstractFactory shapeObj = FactoryProducer.getFactory("SHAPE");
        Shape shape1 = shapeObj.getShape("CIRCLE");
        Shape shape2 = shapeObj.getShape("SQUARE");
        Shape shape3 = shapeObj.getShape("RECTANGLE");

        AbstractFactory colorObj = FactoryProducer.getFactory("COLOR");
        Color color1 = colorObj.getColor("RED");
        Color color2 = colorObj.getColor("GREEN");
        Color color3 = colorObj.getColor("BLUE");
    }
}