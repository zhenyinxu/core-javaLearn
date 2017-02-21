//Student Manage System
package com.xu.t1;

import java.util.Scanner;
public class Sms{
	//fn

	public Student[] stus = new Student[3];
	private int index = 0;
	
	//fn1-add
	public void add(Student stu){
		//Student[] stus = new Student[3];
		//this error make print stu "null";
		if(index>=stus.length){
			Student[] demo = new Student[stus.length+3];
			System.arraycopy(stus,0,demo,0,stus.length);
			stus = demo;
		}
		stus[index++] = stu;
	}

	//fn2-deleteById
	public void deleteById(long id){
		int stuIndex = queryIndexById(id);
		if(stuIndex!=-1){
			for(int i=stuIndex; i<stus.length-stuIndex-1; i++){
				stus[i] = stus[i+1];
			}
			stus[--index] = null;
		}	
	}

	//fn3-update()
	public void update(Student newStu){
		for(int i=0; i<stus.length; i++){

			if(stus[i].getId() == newStu.getId()){
				stus[i].setName(newStu.getName());
				stus[i].setAge(newStu.getAge());
				break;

			}	
		}
	}


	//fn-4-searchById
	public Student searchById(long id){
		int stuIndex = queryIndexById(id);
		return stuIndex ==-1?null:stus[stuIndex];
	}
	//fn5-searchAll
	public Student[] searchAll(){
		Student[] demo = new Student[index];
		System.arraycopy(stus,0,demo,0,index);
		return demo;
	}
	
	//fn-queryIndexById
	private int queryIndexById(long id){
		int stuIndex = -1;
		for(int i=0;i<index;i++){
			if(stus[i].getId() == id){
				stuIndex = i;
				break;
			}
		}
		return stuIndex;
	}
	

	
	//fn-menu
	public void menu(){
		System.out.println(" ------SMS--------");
		System.out.println("|	 1.add        ");
		System.out.println("|	 2.delete     ");
		System.out.println("|	 3.update     ");
		System.out.println("|	 4.searchById ");
		System.out.println("|	 5.searchAll  ");
		System.out.println("|	 help         ");
		System.out.println("|	 exit         ");
		System.out.println(" -------SMS-------");

	}
	
	public static void main(String[] args){
		Sms sms = new Sms();
		sms.menu();
		System.out.println("please enter your cmd:");
		Scanner scanner = new Scanner(System.in);

		while(true){
			System.out.println("please enter cmd:");
			String cmd = scanner.nextLine();
			System.out.println("this is your cmd:"+cmd);
			switch(cmd){
				case "1":
					//this "while(true) make my variable become local not global"
					while(true){
						System.out.println("please enter like this [id#name#age] or enter [break] to back");
						String stuStr = scanner.nextLine();
						if(stuStr.equals("break")){
							break;
						}
						//?arr[] length must be zhiDing;?
						String[] stuArr = stuStr.split("#");
						long id = Long.parseLong(stuArr[0]);
						String name = stuArr[1];
						int age = Integer.parseInt(stuArr[2]);

						Student stu = new Student(id,name,age);
						sms.add(stu);
						System.out.println("succeed add !");
						System.out.println(stu);
					}
					break;

				case "2":
					System.out.println("please enter the id that you want to delete:");
					String idDele = scanner.nextLine();
					sms.deleteById(Long.parseLong(idDele));
					System.out.println("succeed delete !");
					break;
	
				case "3":
					System.out.println("please enter the id  that you want to update:");
					String idUp = scanner.nextLine();
					Student exStu = sms.searchById(Long.parseLong(idUp));
					System.out.println("this is the exInformation that you want to update:"+exStu);
					System.out.println("please enter new information like this [name#age]");
					String stuSpl = scanner.nextLine();
					String[] stuArrUp = stuSpl.split("#");
					String newName = stuArrUp[0];
					int newAge = Integer.parseInt(stuArrUp[1]);
					long newId = Long.parseLong(idUp);
					Student newStu = new Student(newId,newName,newAge);
					sms.update(newStu);
					System.out.println("succeed update !");
					break;
	
				case "4":
					System.out.println("please enter the id that you want to search:");
					String id = scanner.nextLine();
					Student astu = sms.searchById(Long.parseLong(id));
					System.out.println("there is the student that you want to search:");
					System.out.println(astu);
					break;
	
				case "5":
					System.out.println("the following is that all the students information:");
					Student[] stus = sms.searchAll();
					for(Student stu : stus){
						System.out.println(stu);
					}			
					System.out.println("there are "+stus.length+" students !");
				
					break;
	
				case "help":
					sms.menu();
					break;
	
				case "exit":
					System.out.println("see ya !");
					System.exit(0);
					break;

				default:
					System.out.println("error enter !");
			}
		}
		
	}
}