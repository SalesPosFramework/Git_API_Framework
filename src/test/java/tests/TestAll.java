package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.GitHubGetRepoRootPOJO;
import pojo.SenarioValidNamePrivateTrue;

public class TestAll {
	@Test(groups = "smoke")
	public void PostValidOrgNameTokenBody() throws FileNotFoundException {	
		System.out.println(" ------- Create Repo Valid (orgname, token, BodyParam) -----------");
		String url = "orgs/{org}/repos";
		
SenarioValidNamePrivateTrue obj = new SenarioValidNamePrivateTrue();
		
		//obj.setName(name);
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
		//MatcherAssert.assertThat(actualName, Matchers.equalTo(RepoName));
	
}}
