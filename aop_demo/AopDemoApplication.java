package com.xen0n.aop_demo;

import com.xen0n.aop_demo.dao.AccountDAO;
import com.xen0n.aop_demo.dao.MembershipDAO;
import com.xen0n.aop_demo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopDemoApplication {

	static long startTime = System.currentTimeMillis();

	public static void main(String[] args) {
		SpringApplication.run(AopDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO, TrafficFortuneService trafficFortuneService) {
		return runner -> {

			printHeader();

			//demoBeforeAdvice(accountDAO, membershipDAO);
			//demoAfterAdvice(accountDAO, membershipDAO);
			//demoAfterThrowAdvice(accountDAO,membershipDAO);
			//demoAfterAdvice(accountDAO, membershipDAO);
			//demoAroundAdvice(trafficFortuneService);
			//demoAroundAdviceHandleException(trafficFortuneService);
			
			printFooter();
		};
	}

	private void demoAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {
		boolean tripwire = true;
        try {
			String fortune = trafficFortuneService.getTrafficFortune(tripwire);;
			System.out.println("Fortune for the traffic: " + fortune );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	private void demoAroundAdvice(TrafficFortuneService trafficFortuneService) {
		String fortune = trafficFortuneService.getTrafficFortune();
		System.out.println("Fortune for the traffic: " + fortune );

	}

	private void demoAfterAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		List<Account> accounts = null;

		try{
			boolean tripwire = true;
			accounts = accountDAO.getAccounts(tripwire);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void demoAfterThrowAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		List<Account> theAccounts = null;
		try{

			boolean tripwire = true;

			theAccounts = accountDAO.getAccounts(tripwire);

		}catch(Exception e){
			System.out.println("\n\n\nCaught exception: " + e + "\n\n\n");
		}

	}


	private void demoBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {

		Account account = new Account();
		account.setName("Johny");

		accountDAO.addAccount(account);

		accountDAO.addCredit();

		membershipDAO.addAccount();

		membershipDAO.addMember();

		accountDAO.setName("John");

		accountDAO.getName();
	}

	private void demoAfterReturningAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		List<Account> accounts = accountDAO.getAccounts();
		System.out.println(accounts + "\n");
	}


	private static void printHeader() {
		System.out.println("********************************************");
		System.out.println("********        AOP Demo APP        ********");
		System.out.println("********************************************");
		System.out.println("******** Author: M.D.Y.             ********");
		System.out.println("******** Date: 05/08/2025           ********");
		System.out.println("******** Desc: Demo for Spring Aop  ********");
		System.out.println("********************************************");
		System.out.println();
		System.out.println();
	}

	private static void printFooter(){
		long endTime = System.currentTimeMillis();
		long timeElapsed = endTime - startTime;
		System.out.println();
		System.out.println("**********************************");
		System.out.println("**==============================**");
		System.out.println("**     Execution Completed!     **");
		System.out.println("**"+"    Execution Time: "+ timeElapsed +"ms    "+"**");
		System.out.println("**==============================**");
		System.out.println("**********************************");
		System.out.println();
		System.out.println("====== The more you sweat in training, the less you bleed in combat. ======");
		System.out.println();
		System.out.println("Created By M.D.Y - 05/08/2025");
	}
}
