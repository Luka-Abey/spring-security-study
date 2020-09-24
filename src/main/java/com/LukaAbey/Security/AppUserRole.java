package com.LukaAbey.Security;
import static com.LukaAbey.Security.AppUserPermission.COURSE_READ;
import static com.LukaAbey.Security.AppUserPermission.COURSE_WRITE;
import static com.LukaAbey.Security.AppUserPermission.STUDENT_READ;
import static com.LukaAbey.Security.AppUserPermission.STUDENT_WRITE;

import java.util.Set;

import com.google.common.collect.Sets;

public enum AppUserRole {
	STUDENT(Sets.newHashSet()), ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE));
	
	private final Set<AppUserPermission> permissions;

	private AppUserRole(Set<AppUserPermission> permissions) {
		this.permissions = permissions;
	}
	
}
