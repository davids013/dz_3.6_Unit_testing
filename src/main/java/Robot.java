public class Robot {
    public static final double TURNOVER = 360.0;
    private String model;
    private long serialNumber;
    private int weight;
    private Scope scope;
    private long positionX;
    private long positionY;
    private double angle;

    public Robot(String model, long serialNumber, int weight,
                 Scope scope, long positionX, long positionY, double angle) {
        this.model = model;
        this.serialNumber = serialNumber;
        this.weight = weight;
        this.scope = scope;
        this.positionX = positionX;
        this.positionY = positionY;
        this.angle = angle;
    }

    public Robot() {}

    public String getModel() {return model;}

    public long getSerialNumber() {return serialNumber;}

    public int getWeight() {return weight;}

    public Scope getScope() {return scope;}

    public long getPositionX() {return positionX;}

    public long getPositionY() {return positionY;}

    public double getAngle() {return angle;}

    public void setModel(String model) {this.model = model;}

    public void setSerialNumber(long serialNumber) {this.serialNumber = serialNumber;}

    public void setWeight(int weight) {if (weight > 0) this.weight = weight;}

    public void setScope(Scope scope) {this.scope = scope;}

    public void setPositionX(long positionX) {this.positionX = positionX;}

    public void setPositionY(long positionY) {this.positionY = positionY;}

    public void setPosition(long positionX, long positionY) {
        setPositionX(positionX);
        setPositionY(positionY);
    }

    public void setAngle(double angle) {
        while (angle >= TURNOVER) angle -= TURNOVER;
        while (angle < 0) angle += TURNOVER;
        this.angle = angle;
    }

    public void rotate(double angle) {
        setAngle(this.angle + angle);
    }

    public void move(long stepX, long stepY) {
        double tempAngle = Math.atan((double) stepY / stepX) * (180 / Math.PI);
        if (stepX < 0) tempAngle += TURNOVER / 2;
        rotate(tempAngle);
        positionX += stepX;
        positionY += stepY;
    }

    public void jump(int distance, int angle) {

    }

    public String toString() {
        return
                "Robot{model=" + model
                        + ", serialNumber=" + serialNumber
                        + ", weight=" + weight
                        + ", scope=" + scope
                        + ", positionX=" + positionX
                        + ", positionY=" + positionY
                        + ", angle=" + angle
                        + "}";
    }
}