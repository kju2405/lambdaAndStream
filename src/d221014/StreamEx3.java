package d221014;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx3{
	public static void main(String[] args) {
		Student1[] stuArr= {
				new Student1("Á¶¿øµæ",3,300),
				new Student1("¾ç±âÀ±",1,200),
				new Student1("±èµ¿Âù",2,250),
				new Student1("±èÀç¿õ",2,400),
				new Student1("±èÅÂÇö",1,380),
				new Student1("¹Ú¹ü±Ù",3,420),
				new Student1("ÀÌÇö¿ì",2,370)
		};
		
		Stream<Student1> stuStream=Stream.of(stuArr);
		stuStream.sorted(Comparator.comparing(Student1::getBan)
						.thenComparing(Comparator.naturalOrder()))
						.forEach(System.out::println);
		
		stuStream=Stream.of(stuArr);
		IntStream stuScoreStream=stuStream.mapToInt(Student1::getTotalScore);
		
		IntSummaryStatistics stat=stuScoreStream.summaryStatistics();
		System.out.println("count="+stat.getCount());
		System.out.println("sum="+stat.getSum());
		System.out.printf("average=%.2f%n",stat.getAverage());
		System.out.println("min="+stat.getMin());
		System.out.println("max="+stat.getMax());
		
	}
}

class Student1 implements Comparable<Student1>{
	String name;
	int ban;
	int totalScore;
	
	Student1(String name,int ban,int totalScore){
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
	
	public int compareTo(Student1 s) {
		return s.totalScore-this.totalScore;
	}
}