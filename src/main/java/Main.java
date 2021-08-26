public class Main
{
    public static void main(String[] args) {
        Robot robot = new Robot(
                "Chuppy", 772168L, 136, Scope.MILITARY, 0L, 0L, 0d);
        System.out.println(robot);
        robot.setAngle(359.9);
        System.out.println(robot.getAngle());
        robot.rotate(0.5);
        System.out.println(robot.getAngle());
        robot.setAngle(7200);
        robot.move(80L, -20L);
        System.out.println(robot);
    }
}