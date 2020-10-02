package com.LukaAbey.Security;
import static com.LukaAbey.Security.AppUserPermission.COURSE_READ;
import static com.LukaAbey.Security.AppUserPermission.COURSE_WRITE;
import static com.LukaAbey.Security.AppUserPermission.STUDENT_READ;
import static com.LukaAbey.Security.AppUserPermission.STUDENT_WRITE;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum AppUserRole {
	STUDENT(Sets.newHashSet()), ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
	ADMINTRAINEE(Sets.newHashSet(COURSE_READ, STUDENT_READ));

	private final Set<AppUserPermission> permissions;

	AppUserRole(Set<AppUserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<AppUserPermission> getPermissions() {
		return permissions;
	}

	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	}
}