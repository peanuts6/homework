package sso.security;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class AntUrlPathMatcher implements UrlMatcher {

	private boolean requiresLowerCaseUrl;
	private PathMatcher pathMatcher;

	public AntUrlPathMatcher()   {
		this(true);
	}

	public AntUrlPathMatcher(boolean requiresLowerCaseUrl){
		this.requiresLowerCaseUrl = true;
		this.pathMatcher = new AntPathMatcher();
		this.requiresLowerCaseUrl = requiresLowerCaseUrl;
	}

	@Override
	public Object compile(String paramString) {
		if (this.requiresLowerCaseUrl) {
			return paramString.toLowerCase();
		}
		return paramString;
	}

	@Override
	public boolean pathMatchesUrl(Object paramObject, String paramString) {
		if (("/**".equals(paramObject)) || ("**".equals(paramObject))) {
			return true;
		}
		return this.pathMatcher.match((String) paramObject, paramString);
	}

	@Override
	public String getUniversalMatchPattern() {
		return "/**";
	}

	@Override
	public boolean requiresLowerCaseUrl() {
		return this.requiresLowerCaseUrl;
	}

	public void setRequiresLowerCaseUrl(boolean requiresLowerCaseUrl){
		this.requiresLowerCaseUrl = requiresLowerCaseUrl;
	}

	public String toString() {
		return super.getClass().getName() + "[requiresLowerCase='"+ this.requiresLowerCaseUrl + "']";
	}
}
