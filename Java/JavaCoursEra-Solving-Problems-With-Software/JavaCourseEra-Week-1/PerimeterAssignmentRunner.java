import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here

        int no_of_points = 0;
        for (Point point : s.getPoints())
        {

          no_of_points++;

        }

        return no_of_points;
    }

    public double getAverageLength(Shape s) {
        // Put code here



        return (double)getPerimeter(s)/getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        // Put code here

        Point last = s.getLastPoint();
        double max_dist = 0;
        for (Point p : s.getPoints() ) {

          double dist = last.distance(p);
          if (dist > max_dist) {
            max_dist = dist;
          }
          last = p;

        }


        return max_dist;
    }

    public double getLargestX(Shape s) {
        // Put code here

        double max_x = s.getLastPoint().getX();
        for(Point point : s.getPoints())
        {

          double curr = point.getX();

          if(curr > max_x)
            max_x = curr;

        }

        return max_x;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here

        DirectoryResource dr = new DirectoryResource();
        double max_val = 0;

        for (File f : dr.selectedFiles())
        {

          FileResource fr = new FileResource(f);
          Shape shape = new Shape(fr);
          if (getPerimeter(shape) > max_val) {
            max_val = getPerimeter(shape);
          }

        }

        return max_val;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double max_val = 0;
        File file = null;

        for (File f : dr.selectedFiles())
        {

          FileResource fr = new FileResource(f);
          Shape shape = new Shape(fr);
          if (getPerimeter(shape) > max_val) {
            max_val = getPerimeter(shape);
            file = f;
          }

        }

        return file.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("Total number of points = " + getNumPoints(s));
        System.out.println("Average length = " + getAverageLength(s));
        System.out.println("Largest side = " + getLargestSide(s));
        System.out.println("Maximum x = " + getLargestX(s));
        testPerimeterMultipleFiles();
        testFileWithLargestPerimeter();
    }

    public void testPerimeterMultipleFiles() {
        // Put code here

        System.out.println("getLargestPerimeterMultipleFiles = " + getLargestPerimeterMultipleFiles());

    }

    public void testFileWithLargestPerimeter() {
        // Put code here

        System.out.println(getFileWithLargestPerimeter());

    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
