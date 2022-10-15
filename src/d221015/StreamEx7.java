package d221015;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Student1{
	String name;
	boolean isMale;
	int hak;
	int ban;
	int score;
	Student1(String name,boolean isMale, int hak,int ban,int score){
		this.name=name;
		this.isMale=isMale;
		this.hak=hak;
		this.ban=ban;
		this.score=score;
	}
	
	String getName() {
		return name;
	}
	boolean isMale() {
		return isMale;
	}
	int getHak() {
		return hak;
	}
	int getBan() {
		return ban;
	}
	int getScore() {
		return score;
	}
	public String toString() {
		return String.format("[%s, %s, %d학년 %d반, %3d점]", name,isMale?"남":"여",hak,ban,score);
	}
	
	enum Level{HIGH,MID,LOW}
}
public class StreamEx7{
	public static void main(String[] args) {
		Student1[] stuArr= {
				new Student1("김재웅",true,1,1,300),
				new Student1("김건웅",true,1,2,250),
				new Student1("김주현",false,1,3,200),
				new Student1("김규랑",false,2,1,300),
				new Student1("김재원",true,2,3,250),
				new Student1("김연재",false,2,2,150)
		};
		
		System.out.printf("1. 단순분할(성별로 분할)%n");
		Map<Boolean,List<Student1>> stuBySex=Stream.of(stuArr).collect(Collectors.partitioningBy(Student1::isMale));
		List<Student1> maleStudent=stuBySex.get(true);
		List<Student1> femaleStudent=stuBySex.get(false);
		
		for(Student1 s:maleStudent) System.out.println(s);
		for(Student1 s:femaleStudent) System.out.println(s);
		
		
		System.out.printf("%n2. 단순분할 + 통계(성별 학생수)%n");
		Map<Boolean, Long> stuNumBySex=Stream.of(stuArr).collect(Collectors.partitioningBy(Student1::isMale,Collectors.counting()));
		
		System.out.println("남학생 수 : "+stuNumBySex.get(true));
		System.out.println("여학생 수 : "+stuNumBySex.get(false));
		
		System.out.printf("%n3. 단순분할 + 통계(성별 1등)%n");
		Map<Boolean,Optional<Student1>> topScoreBySex=Stream.of(stuArr).collect(Collectors.partitioningBy(Student1::isMale,Collectors.maxBy(Comparator.comparingInt(Student1::getScore))));
		System.out.println("남학생 1등 : "+topScoreBySex.get(true));
		System.out.println("여학생 1등 : "+topScoreBySex.get(false));
		
		Map<Boolean,Student1> topScoreBySex2=Stream.of(stuArr).collect(Collectors.partitioningBy(Student1::isMale,
				Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Student1::getScore)),Optional::get)));
		System.out.println("남학생 1등 : "+topScoreBySex2.get(true));
		System.out.println("여학생 1등 : "+topScoreBySex2.get(false));
		
		System.out.printf("%n4. 다준분할(성별 불합격자, 250점이하)%n");
		Map<Boolean,Map<Boolean,List<Student1>>> failedStuBySex=Stream.of(stuArr).collect(Collectors.partitioningBy(Student1::isMale,Collectors.partitioningBy(s->s.getScore()<=250)));
		List<Student1> failedMaleStu=failedStuBySex.get(true).get(true);
		List<Student1> failedFemailStu=failedStuBySex.get(false).get(true);
		
		for(Student1 s:failedMaleStu) {
			System.out.println(s);
		}			
		System.out.println();
		for(Student1 s:failedFemailStu) {
			System.out.println(s);
		}
			
		
		
	}
}