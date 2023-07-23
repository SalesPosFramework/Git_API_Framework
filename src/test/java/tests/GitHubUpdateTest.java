package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import core.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GitHubUpdateTest extends BaseTest {
	@Test(groups = "smoke")
	public void PatchValidOwnerRepoToken() throws FileNotFoundException {	
		System.out.println(" ------- Update Repo Valid (owner, Repo Name, token) -----------");
		String url = "/repos/{owner}/{repo}";
		
		FileInputStream fis = new FileInputStream("src/test/resources/JsonBody/Patch1.json");
		
		RestAssured
			.given()
				.header("Authorization","Bearer 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")
				.pathParam("owner","OrgKavi")
				.pathParam("repo","repo_23July")
				.body(fis)
				.contentType(ContentType.JSON)
			.when()
				.log().all()
				.patch(url)
			.then()
				.log().all()
				.assertThat()
				.statusCode(200);

	}
	

	@Test(groups = "smoke")
	public void PatchValidOwnerPrivateRepoToken() {	
		System.out.println(" ------- Update Repo Valid (orgname, Reponame, token) -----------");
		String url = "/repos/{owner}/{repo}";
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("src/test/resources/JsonBody/Patch1.json");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RestAssured
			.given()
				.header("Authorization","Bearer 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")
				.pathParam("owner","OrgKavi")
				.pathParam("repo","repo1061984045")
				.body(fis)
				.contentType(ContentType.JSON)
			.when()
				.log().all()
				.patch(url)
			.then()
				.log().all()
				.assertThat()
				.statusCode(200);

	}
	
	@Test(groups = "regression")
	public void PatchInValidOwnerValidRepoToken() {	
		System.out.println(" ------- Update Repo InValid (Owner) Valid (Reponame, token) -----------");
		String url = "/repos/{owner}/{repo}";
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("src/test/resources/JsonBody/Patch1.json");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RestAssured
			.given()
				.header("Authorization","Bearer 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")
				.pathParam("owner","OrgKai")
				.pathParam("repo","repo-58291057")
				.body(fis)
				.contentType(ContentType.JSON)
			.when()
				.log().all()
				.patch(url)
			.then()
				.log().all()
				.assertThat()
				.statusCode(404);
	}
	
	@Test(groups = "regression")
	public void PatchValidOwnerInValidRepoNoScopeToken() throws FileNotFoundException {	
		System.out.println(" ------- Update Repo Valid (owner) Invlaid ( Repo Name) No Scope (token) -----------");
		String url = "/repos/{owner}/{repo}";
		
		FileInputStream fis = new FileInputStream("src/test/resources/JsonBody/Patch.json");
		
		RestAssured
			.given()
				.header("Authorization","Bearer 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")
				.pathParam("owner","OrgKavi")
				.pathParam("repo","Kavita")
				.body(fis)
				.contentType(ContentType.JSON)
			.when()
				.log().all()
				.patch(url)
			.then()
				.log().all()
				.assertThat()
				.statusCode(404);

	}

	@Test(groups = "regression")
	public void PatchValidOwnerInValidRepoNoToken() throws FileNotFoundException {	
		System.out.println(" ------- Update Repo Valid (owner) Invlaid ( Repo Name) No (token) -----------");
		String url = "/repos/{owner}/{repo}";
		
		FileInputStream fis = new FileInputStream("src/test/resources/JsonBody/Patch.json");
		
		RestAssured
			.given()
				.pathParam("owner","OrgKavi")
				.pathParam("repo","Kaa")
				.body(fis)
				.contentType(ContentType.JSON)
			.when()
				.log().all()
				.patch(url)
			.then()
				.log().all()
				.assertThat()
				.statusCode(404);

	}
}

