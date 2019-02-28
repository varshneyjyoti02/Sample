package com.capi.ui;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.capi.beans.Trainer;
import com.capi.exception.NameIsNumeric;

import com.capi.service.FeedBackService;
import com.capi.service.FeedBackServiceImpl;

public class FeedBackMain {

	public static void main(String[] args)
	{
		FeedBackService fs=new FeedBackServiceImpl();
		
		Scanner sc=new Scanner(System.in);
		
		while(true)
		{
			System.out.println("Enter choice");
			
			int ch=sc.nextInt();
			
			switch(ch)
			{
			case 1:
				System.out.println("Enter Name");
				String name=sc.next();
				sc.nextLine();
				
				if(Pattern.compile("[0-9]").matcher(name).find())
				{
					try {
						throw new NameIsNumeric();
					} catch (NameIsNumeric e) {
						// TODO Auto-generated catch block
						System.out.println(e);
						break;
					}
				}
				
				
				System.out.println("Enter course Name");
				String courseName=sc.nextLine();
				
				System.out.println("Enter start date");
				String sDate=sc.nextLine();
				
				System.out.println("Enter end date");
				String eDate=sc.nextLine();
				
				LocalDate startDate=LocalDate.parse(sDate);
				LocalDate endDate=LocalDate.parse(eDate);
				System.out.println("Enter rating");
				int rating=sc.nextInt() ;
				sc.nextLine();
				Trainer t=new Trainer();
				
				t.setName(name);
				t.setCourseName(courseName);
				t.setStartDate(startDate);
				t.setEndDate(endDate);
				t.setRating(rating);
				
				try {
					fs.addFeedback(t);
				} catch (NameIsNumeric e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
				
			System.out.println(fs.getTrainerList(rating));
				
				
				break;
			case 2:
				System.out.println("Enter rating");
				int rating1=sc.nextInt() ;
				sc.nextLine();
				
				HashMap<Integer, Trainer> hm;
				
					hm = fs.getTrainerList(rating1);
				
				
				for(Map.Entry<Integer, Trainer> entry:hm.entrySet())
				{
	 				
	 				System.out.print("Sales ID: ");
	 				
	 				System.out.println(entry.getKey());
	 				System.out.println("Name:"+entry.getValue().getName());
	 				System.out.println("Course Name:"+entry.getValue().getCourseName());
	 				System.out.println("start date:"+entry.getValue().getStartDate());
	 				System.out.println("end date:"+entry.getValue().getEndDate());
	 				System.out.println("rating:"+entry.getValue().getRating());
				
	 				
	 				
				}
				
				
				
				
				break;
			}
		}
		
		
	}
	
}
