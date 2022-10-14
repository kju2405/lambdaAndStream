package d221014;

import java.util.Comparator;
import java.util.stream.Stream;

public class StreamEx1{
	public static void main(String[] args) {
		Stream<Student> studentStream=Stream.of(
				new Student("±èÀç¿õ",3,300),
				new Student("±èÇåÀ±",1,350),
				new Student("±è³²¼÷",2,330),
				new Student("±è°Ç¿õ",3,270)
				);
		studentStream.sorted(Comparator.comparing(Student::getBan)
				.thenComparing(Comparator.naturalOrder()))
				.forEach(System.out::println);
	}
}

class Student implements Comparable<Student>{
	String name;
	int ban;
	int totalScore;
	Student(String name,int ban,int totalScore){
		this.name=name;
		this.ban=ban;
		this.totalScore=totalScore;
	}
	
	public String toString() {
		return String.format("[%s, %d, %d]", name,ban,totalScore);
	}
	
	String getName() {
		return name;
	}
	int getBan() {
		return ban;
	}
	int getTotalScore() {
		return totalScore;
	}
	
	public int compareTo(Student s) {
		return s.totalScore-this.totalScore;	//³»¸²Â÷¼ø Á¤·Ä
	}
}