package cn.lanyu.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@Component
public class StatusCache implements InitializingBean{

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/*private static Cache<String,Status> cache;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		cache = CacheBuilder.newBuilder().expireAfterAccess(Integer.MAX_VALUE, TimeUnit.MINUTES).initialCapacity(100).maximumSize(2000)
				.build();
	}
	
	public static void put(String key,Status value) {
		cache.put(key, value);
	}
	
	public static Status get(String key) {
		return cache.getIfPresent(key);
	}
	
	public static void invalidate(String key) {
		cache.invalidate(key);
	}
	
	public static Cache getCache() {
		return cache;
	}*/
}