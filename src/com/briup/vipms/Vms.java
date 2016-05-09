package com.briup.vipms;
import java.util.Scanner;
public class Vms
{
	public int index=0;
	public Vip[] vip=new Vip[3];
    /**菜单*/
	public void Menu(){
		System.out.println("**********超市VIP顾客信息管理系统**********");
		System.out.println("*1.查询所有超市VIP顾客信息");
		System.out.println("*2.添加超市VIP顾客信息");
		System.out.println("*3.删除超市VIP顾客信息");
		System.out.println("*4.修改超市VIP顾客信息");
		System.out.println("*5.查询超市VIP顾客信息");
		System.out.println("*exit.退出超市VIP顾客信息管理系统");
		System.out.println("*help.帮助列表");
		System.out.println("************************************");
	}
	/**代码开始入口*/
	public static void main(String[] args){
		Vms vms=new Vms();
		Scanner sc=new Scanner(System.in);
		vms.Menu();
		while(true){
			System.out.print("请输入功能编号:");
			String option=sc.nextLine();
			switch(option){
				case "1":
					System.out.println("以下是所有超市VIP顾客信息：");
				    Vip[] vips=vms.findAll();
					for(Vip v:vips){
						System.out.println(v);
					}
					System.out.println("总人数为："+vms.index+"人");
					break;
				case "2":
				 while(true){
					System.out.println("请输入超市VIP顾客信息，格式为【id#姓名#年龄】或输入【break】返回上一级");
				    String vipstr=sc.nextLine();
					if(vipstr.equals("break")) break;
					String[] arr=vipstr.split("#");
					long id=Long.parseLong(arr[0]);
					String name=arr[1];
					int age=Integer.parseInt(arr[2]);
					Vip v=new Vip(id,name,age);
					vms.add(v);
					System.out.println("添加成功！");
				 }
					break;
				case "3":
					while(true){
					System.out.println("请输入需要删除信息的超市VIP顾客id或输入【break】返回上一级");
				    String vipstr=sc.nextLine();
					if(vipstr.equals("break")) break;
					long id=Long.parseLong(vipstr);
					int num=vms.getIndexById(id);
					if(num==-1){
						System.out.println("删除的超市VIP顾客信息不存在！");
						break;
					}
					vms.deleteById(id);
					System.out.println("删除成功！");


				}
					break;
				case "4":
					while(true){
				       System.out.println("请输入需要修改信息的超市VIP顾客id或输入【break】返回上一级");
				       String vipstr=sc.nextLine();
					   if(vipstr.equals("break")) break;
					   long id=Long.parseLong(vipstr);
					   int num=vms.getIndexById(id);
					   if(num==-1){
						System.out.println("需要修改的超市VIP顾客信息不存在！");
						continue;
					   }
					   System.out.print("原超市VIP顾客信息为："+vms.vip[num]);
					   System.out.println("请输入修改信息，格式为【姓名#年龄】或输入【break】退出修改！");
					   String vipstrnew=sc.nextLine();
					   if(vipstrnew.equals("break")) break;
					   String[] arr=vipstrnew.split("#");
					   String name=arr[0];
					   int age=Integer.parseInt(arr[1]);
					   Vip v=new Vip(id,name,age);
					   vms.update(v);
					   System.out.println("修改成功！");
				    }
					break;
				case "5":					
					while(true){
				       System.out.println("请输入需要查询信息的超市VIP顾客id或输入【break】返回上一级");
				       String vipstr=sc.nextLine();
					   if(vipstr.equals("break")) break;
					   long id=Long.parseLong(vipstr);
					   int num=vms.getIndexById(id);
					   if(num==-1){
						System.out.println("需要查询的超市VIP顾客信息不存在！");
						continue;
					   }
					   Vip v=vms.findById(id);
					   System.out.println(v);
				    }
					break;
				case "exit":
					System.out.println("欢迎再次使用！");
					System.exit(0);
					break;
				case "help":
					vms.Menu();
					break;
				default:
					System.out.println("输入错误！请按提示输入！");
					break;
					
			}
		}
	}
	/**每个超市VIP顾客的索引*/
	public int getIndexById(long id){
		int num=-1;
		for(int i=0;i<index;i++){
	       if(vip[i].getid()==id){
			   num=i;
		   }
		}
		return num; 
	}
	/**查找所有超市VIP顾客*/
	public Vip[] findAll(){
		Vip[] demo=new Vip[index];
		System.arraycopy(vip,0,demo,0,index);
		return demo;
	}
	/**添加超市VIP顾客信息*/
	public void add(Vip v){
		if(index>=vip.length){
			Vip[] demo=new Vip[index+3];
			System.arraycopy(vip,0,demo,0,index);
			vip=demo;
		}
		vip[index]=v;
		index++;
		
	}
	/**删除*/
	public void deleteById(long id){
		int num=getIndexById(id);
		for(int i=num;i<index-1;i++){
			vip[i]=vip[i+1];
		}
		vip[index-1]=null;
		index--;
	}
	/**更新信息操作*/
	public void update(Vip v){
		int num=getIndexById(v.getid());
		vip[num]=v;
	}
	/**查询操作*/
	public Vip findById(long id){
		int num=getIndexById(id);
		return vip[num];
	}


}