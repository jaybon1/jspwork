package com.cos.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoProfile {
	
	private String id;
	private KakaoAccount kakao_account;

	@Data
	public class KakaoAccount {
		private Profile profile;
		private String email;
		private boolean has_email;

		@Data
		public class Profile {
			private String nickname;
			private String profile_image_url;

		}
	}
}