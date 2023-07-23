package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import core.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.GitHubGetRepoRootPOJO;
import pojo.SenarioValidNamePrivateTrue;


public class GitHubPostTest extends BaseTest {

	@Test(priority = 1,groups = "smoke")
	public void PostValidOrgNameTokenBody() throws FileNotFoundException {	
		System.out.println(" ------- Create Repo Valid (orgname, token, BodyParam) -----------");
		String url = "orgs/{org}/repos";
		
		FileInputStream fis = new FileInputStream("src/test/resources/JsonBody/Post.json");
		
		RestAssured
			.given()
				.header("Authorization","Bearer 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")
				.pathParam("org","OrgKavi")
				.body(fis)
				.contentType(ContentType.JSON)
			.when()
				.log().all()
				.post(url)
			.then()
				.log().all()
				.assertThat()
				.statusCode(201)
				.and().assertThat()
				.body("name",Matchers.equalTo("KavitaSB"));
	}
	
	@Test(priority = 2,groups = "smoke")
	public void PostValidOrgNameTokenBodyPrivate(){	
		System.out.println(" ------- Create Private Repo Valid (orgname, token, BodyParam) -----------");
		String url = "orgs/{org}/repos";
		
		Random rnd = new Random();
		String prefix = "repo";
		int rndNum = rnd.nextInt();
		
		String RepoName = prefix+rndNum;
		
		SenarioValidNamePrivateTrue obj = new SenarioValidNamePrivateTrue();
		
		obj.setName(RepoName);
		obj.setPrivate(false);
		
		GitHubGetRepoRootPOJO res = RestAssured
			.given()
				.header("Authorization","Bearer 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")
				.pathParam("org","OrgKavi")
				.body(obj)
			.when()
				.log().all()
				.post(url)
			.then()
				.log().all()
				.assertThat()
				.statusCode(201)
				.extract().as(GitHubGetRepoRootPOJO.class);
		
		String actualName = res.getName();
		System.out.println(actualName);
		MatcherAssert.assertThat(actualName, Matchers.equalTo(RepoName));
	}
	
	
		@Test(priority = 3, groups = "regression")
		public void PostInValidOrgNameValidTokenBody() throws FileNotFoundException {	
			System.out.println(" ------- Create Repo Invalid (orgname) & Valid  (token, BodyParam) -----------");
			String url = "orgs/{org}/repos";
			
			FileInputStream fis = new FileInputStream("src/test/resources/JsonBody/Post.json");
			
			RestAssured
				.given()
					.header("Authorization","Bearer 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")
					.pathParam("org","OrKavi")
					.body(fis)
				.when()
					.log().all()
					.post(url)
				.then()
					.log().all()
					.assertThat()
					.statusCode(404);
	}
		
		
		
		@Test(priority = 4,groups = "regression")
		public void PostValidOrgNameTokenInvalidBody() throws FileNotFoundException {	
			System.out.println(" ------- Create Repo Invalid (BodyParam) & Valid  (orgname, token) -----------");
			String url = "orgs/{org}/repos";
			
			FileInputStream fis = new FileInputStream("src/test/resources/JsonBody/Post3.json");
			
			RestAssured
				.given()
					.header("Authorization","Bearer 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")
					.pathParam("org","OrgKavi")
					.body(fis)
				.when()
					.log().all()
					.post(url)
				.then()
					.log().all()
					.assertThat()
					.statusCode(422);
	}
		
		@Test(priority = 5,groups = "regression")
		public void PostValidOrgNameNoTokenInvalidBody() throws FileNotFoundException {	
			System.out.println(" ------- Create Repo Invalid  BodyParam (No Name, Private = true), No Token & Valid  (orgname) -----------");
			String url = "orgs/{org}/repos";
			
			FileInputStream fis = new FileInputStream("src/test/resources/JsonBody/Post4.json");
			
			
			RestAssured
				.given()
					.pathParam("org","OrgKavi")
					.body(fis)
				.when()
					.log().all()
					.post(url)
				.then()
					.log().all()
					.assertThat()
					.statusCode(401);
	}
		@Test(priority = 6,groups = "regression")
		public void PostValidOrgNameNoTokenBodyPrivate() throws FileNotFoundException {	
			System.out.println(" ------- Create Private Repo Valid (orgname, BodyParam) & No Token -----------");
			String url = "orgs/{org}/repos";
			
			FileInputStream fis = new FileInputStream("src/test/resources/JsonBody/Post2.json");
			
			RestAssured
				.given()
					.pathParam("org","OrgKavi")
					.body(fis)
				.when()
					.log().all()
					.post(url)
				.then()
					.log().all()
					.assertThat()
					.statusCode(401);
		}
		

		@Test(priority = 7,groups = "smoke")
		public void PostValidOrgNameNoTokenBody() throws FileNotFoundException {	
			System.out.println(" ------- Create Repo Valid (orgname, BodyParam) & Invalid (Token) -----------");
			String url = "orgs/{org}/repos";
			
			FileInputStream fis = new FileInputStream("src/test/resources/JsonBody/Post2.json");
			
			RestAssured
				.given()
					.header("Authorization","Bearer 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")
					.pathParam("org","OrgKavi")
					.body(fis)
				.when()
					.log().all()
					.post(url)
				.then()
					.log().all()
					.assertThat()
					.statusCode(201);
		}
		
		
		@Test(priority = 8,groups = "regression")
		public void PostInValidOrgNameNoScopeTokenValidBodyPrivate() throws FileNotFoundException {	
			System.out.println(" ------- Create Private Repo Invalid (OrgName) NoScope (Token) Valid ( BodyParam) -----------");
			String url = "orgs/{org}/repos";
			
			FileInputStream fis = new FileInputStream("src/test/resources/JsonBody/Post2.json");
			
			RestAssured
				.given()
					.header("Authorization","Bearer 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")
					.pathParam("org","OrKavi")
					.body(fis)
				.when()
					.log().all()
					.post(url)
				.then()
					.log().all()
					.assertThat()
					.statusCode(404);
		}
}
