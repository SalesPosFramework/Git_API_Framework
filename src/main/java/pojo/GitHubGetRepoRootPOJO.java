package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubGetRepoRootPOJO {

	private String name;
	private String full_name;
	private String description;
	private boolean privateVal;
	
	private GitHubOwnerPOJO owner;
	private GitHubPermissionsPOJO permissions;
	private GitHubOrganizationPOJO organization;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isPrivate() {
		return privateVal;
	}
	public void setPrivate(boolean privateVal) {
		this.privateVal = privateVal;
	}
	public GitHubOwnerPOJO getOwner() {
		return owner;
	}
	public void setOwner(GitHubOwnerPOJO owner) {
		this.owner = owner;
	}
	public GitHubPermissionsPOJO getPermissions() {
		return permissions;
	}
	public void setPermissions(GitHubPermissionsPOJO permissions) {
		this.permissions = permissions;
	}
	public GitHubOrganizationPOJO getOrganization() {
		return organization;
	}
	public void setOrganization(GitHubOrganizationPOJO organization) {
		this.organization = organization;
	}
		
	
}
