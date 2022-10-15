package d221015;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx6{
	public static void main(String[] args) {
		Student[] stuArr= {
				new Student("�����",1,400),
				new Student("��ǿ�",2,350),
				new Student("�����",3,450),
				new Student("������",1,370),
				new Student("��Է�",2,250),
				new Student("�迬��",3,250)
		};
		List<String> names=Stream.of(stuArr).map(Student::getName).collect(Collectors.toList());
		System.out.println(names);
		
		Student[] stuArr2=Stream.of(stuArr).toArray(Student[]::new);
		for(Student s:stuArr2) {
			System.out.println(s);
		}
		
		Map<String,Student> stuMap=Stream.of(stuArr).collect(Collectors.toMap(s->s.getName(), s->s));
		
		for(String name : stuMap.keySet()) {
			System.out.println(name+"-"+stuMap.get(name));
		}
		
		long count=Stream.of(stuArr).collect(Collectors.counting());
		long totalScore=Stream.of(stuArr).collect(Collectors.summingInt(Student::getTotalScore));
		
		System.out.println("count="+count);
		System.out.println("totalScore="+totalScore);
		
		totalScore=Stream.of(stuArr).collect(Collectors.reducing(0,Student::getTotalScore,Integer::sum));
		System.out.println("totalScore="+totalScore);
		Optional<Student> topStudent=Stream.of(stuArr).collect(Collectors.maxBy(Comparator.comparingInt(Student::getTotalScore)));
		System.out.println("topStudent="+topStudent.get());
		
		IntSummaryStatistics stat=Stream.of(stuArr).collect(Collectors.summarizingInt(Student::getTotalScore));
		System.out.println(stat);
		
		String stuNames=Stream.of(stuArr).map(Student::getName).collect(Collectors.joining(",","[","]"));
		System.out.println(stuNames);
		
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
		return s.totalScore-this.totalScore;
	}
}