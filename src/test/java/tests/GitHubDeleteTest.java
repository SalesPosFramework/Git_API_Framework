package tests;

import org.testng.annotations.Test;

import core.BaseTest;
import io.restassured.RestAssured;

public class GitHubDeleteTest extends BaseTest{

	@Test(groups = "smoke")
	public void DeleteValidOwnerRepoToken(){	
		System.out.println(" ------- Delete Repo Valid (owner, Repo Name, token) -----------");
		String url = "/repos/{owner}/{repo}";
		
		RestAssured
			.given()
				.header("Authorization","Bearer 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")
				.pathParam("owner","OrgKavi")
				.pathParam("repo","repo573857925")
			.when()
				.log().all()
				.delete(url)
			.then()
				.log().all()
				.assertThat()
				.statusCode(204);

	}
	@Test(groups = "smoke")
	public void DeleteValidOwnerPrivateRepoToken(){	
		System.out.println(" ------- Delete Repo Valid (owner, Private Repo Name, token) -----------");
		String url = "/repos/{owner}/{repo}";
		
		RestAssured
			.given()
				.header("Authorization","Bearer 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")
				.pathParam("owner","OrgKavi")
				.pathParam("repo","KavitaSB6")
			.when()
				.log().all()
				.delete(url)
			.then()
				.log().all()
				.assertThat()
				.statusCode(204);

	}
	@Test(groups = "regression")
	public void DeleteInValidOwnerValidRepoToken(){	
		System.out.println(" ------- Delete Repo InValid (Owner) Valid ( Repo Name, token) -----------");
		String url = "/repos/{owner}/{repo}";
		
		RestAssured
			.given()
				.header("Authorization","Bearer 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")
				.pathParam("owner","Orgavi")
				.pathParam("repo","Kavita_4_6_23")
			.when()
				.log().all()
				.delete(url)
			.then()
				.log().all()
				.assertThat()
				.statusCode(404);

	}
	@Test(groups = "regression")
	public void DeleteValidOwnerInValidRepoNoScopeToken(){	
		System.out.println(" ------- Delete Repo Valid (Owner) InValid ( Repo Name, token) -----------");
		String url = "/repos/{owner}/{repo}";
		
		RestAssured
			.given()
				.header("Authorization","Bearer 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")
				.pathParam("owner","OrgKavi")
				.pathParam("repo","Kavita_4_6_")
			.when()
				.log().all()
				.delete(url)
			.then()
				.log().all()
				.assertThat()
				.statusCode(404);

	}
	
	@Test(groups = "regression")
	public void DeleteValidOwnerInValidRepoNoToken(){	
		System.out.println(" ------- Delete Repo Valid (Owner) InValid ( Repo Name, token) -----------");
		String url = "/repos/{owner}/{repo}";
		
		RestAssured
			.given()
				.pathParam("owner","OrgKavi")
				.pathParam("repo","Kavita_4_6_")
			.when()
				.log().all()
				.delete(url)
			.then()
				.log().all()
				.assertThat()
				.statusCode(404);

	}
}
