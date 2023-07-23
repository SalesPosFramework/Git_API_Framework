package tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import core.BaseTest;
import io.restassured.RestAssured;

public class GitHubListOrgReposTest extends BaseTest{

	@Test(groups = "smoke")
	public void listOrgRepos() {	
		System.out.println(" ------- List Org Repos Test -----------");
		String url = "orgs/{org}/repos";
		
		RestAssured
			.given()
				.pathParam("org","OrgKavi")
			.when()
				.log().all()
				.get(url)
			.then()
				.assertThat()
					.statusCode(200);
	}
	
	@Test(groups = "regression")
	public void listOrgInvalidOrgName() {
		System.out.println(" ------- List Org Repos Invalid Org Test -----------");
		String url = "orgs/{org}/repos";
		
		RestAssured
			.given()
				.pathParam("org","asssaaaaddggqq")
			.when()
				.log().all()
				.get(url)
			.then()
					.log().body()
					.assertThat()
					.statusCode(404)
				.and().assertThat()
					.body("message", Matchers.equalTo("Not Found"));
	}

	@Test(groups = "smoke")
	public void listOrgPrivateWithValidToken() {	
		//System.out.println(" ------- List Org Repos Test -----------");
		String url = "orgs/{org}/repos";
		
		RestAssured
			.given()
				.header("Authorization","Bearer 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")
				.pathParam("org","OrgKavi")
				.queryParam("type", "private")
			.when()
				.log().all()
				.get(url)
			.then()
				.log().body()
				.assertThat()
				.statusCode(200)
				.and().assertThat()
				.body("private[0]",Matchers.equalTo(true));
					
	}
	
	@Test(groups = "regression")
	public void listOrgPerPageReposValid() {	
		//System.out.println(" ------- List Org Repos Test -----------");
		String url = "orgs/{org}/repos";
		
		RestAssured
			.given()
				.queryParam("per_page", 1)
				.pathParam("org","OrgKavi")
			.when()
				.log().all()
				.get(url)
			.then()
				.log().body()
				.assertThat()
				.statusCode(200)
				.and().assertThat()
				.body("size()",Matchers.equalTo(1));
					
	}
	
	@Test(groups = "regression")
	public void listOrgPrivateRepoNoToken() {	
		//System.out.println(" ------- List Org Repos Test -----------");
		String url = "orgs/{org}/repos";
		
		RestAssured
			.given()
				.header("Authorization","Bearer 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")
				.pathParam("org","OrgKavi")
				.queryParam("type", "private")
			.when()
				.log().all()
				.get(url)
			.then()
				.log().body()
				.assertThat()
				.statusCode(200)
				.and().assertThat()
				.body("private[0]",Matchers.equalTo(true));
					
	}
	
}
