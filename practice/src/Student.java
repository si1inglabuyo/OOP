class Student {
    private int[] grades;
    private double average;

    public Student(int[] grades) {
        this.grades = grades;
        calculateAverage();
    }

    public void calculateAverage() {
        int sum = 0;
        for(int grade : grades) {
            sum += grade;
        }
        average = sum / 5.0;
    }

    public double getAverage() {
        return average;
    }
}
