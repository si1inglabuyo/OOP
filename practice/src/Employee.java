class Employee {
    private String name;
    private double basicSalary;
    private int overtimeHours;

    public Employee(String name, double basicSalary, int overtimeHours) {
        this.name = name;
        this.basicSalary = basicSalary;
        this.overtimeHours = overtimeHours;
    }

    public String getName() {
        return name;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public int getOvertimeHours() {
        return overtimeHours;
    }

    public double getOvertimePay() {
        return overtimeHours * 200;
    }

    public double getGrossSalary() {
        return basicSalary + getOvertimePay();
    }
}
