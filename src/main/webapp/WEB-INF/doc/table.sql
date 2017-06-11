-- db drop
drop database travel;

-- create db
create database travel;

-- use db
use travel;

-- 회원
CREATE TABLE user (
	userCode INT UNSIGNED NOT NULL COMMENT '회원번호', -- 회원번호
	id       VARCHAR(100) NOT NULL COMMENT '아이디', -- 아이디
	password VARCHAR(100) NOT NULL COMMENT '비밀번호', -- 비밀번호
	enabled  TINYINT(1)   NOT NULL DEFAULT 1 COMMENT '사용여부' -- 사용여부
) ENGINE=InnoDB DEFAULT CHARSET=utf8
COMMENT '회원';

-- 회원
ALTER TABLE user
	ADD CONSTRAINT PK_user -- 회원 기본키
		PRIMARY KEY (
			userCode -- 회원번호
		);

ALTER TABLE user
	MODIFY COLUMN userCode INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '회원번호';

ALTER TABLE user
	AUTO_INCREMENT = 1;

-- 회원상세
CREATE TABLE userDetail (
	userCode  INT UNSIGNED NOT NULL COMMENT '회원번호', -- 회원번호
	name      VARCHAR(12)  NOT NULL COMMENT '이름', -- 이름
	age       INT UNSIGNED NOT NULL COMMENT '나이', -- 나이
	sex       CHAR(6)      NOT NULL COMMENT '성별', -- 성별
	location  VARCHAR(20)  NOT NULL COMMENT '지역', -- 지역
	meanPoint FLOAT(3,2)   NOT NULL DEFAULT 0 COMMENT '평점' -- 평점
) ENGINE=InnoDB DEFAULT CHARSET=utf8
COMMENT '회원상세';

-- 회원상세
ALTER TABLE userDetail
	ADD CONSTRAINT PK_userDetail -- 회원상세 기본키
		PRIMARY KEY (
			userCode -- 회원번호
		);

-- 여행
CREATE TABLE travel (
	travelCode INT UNSIGNED NOT NULL COMMENT '여행번호', -- 여행번호
	userCode   INT UNSIGNED NOT NULL COMMENT '작성회원번호', -- 작성회원번호
	title      VARCHAR(20)  NOT NULL COMMENT '제목', -- 제목
	content    TEXT         NULL     COMMENT '내용', -- 내용
	writeDate  DATE         NOT NULL COMMENT '작성일' -- 작성일
) ENGINE=InnoDB DEFAULT CHARSET=utf8
COMMENT '회원코드는 작성자 이름을 알아내기 위함';

-- 여행
ALTER TABLE travel
	ADD CONSTRAINT PK_travel -- 여행 기본키
		PRIMARY KEY (
			travelCode -- 여행번호
		);

ALTER TABLE travel
	MODIFY COLUMN travelCode INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '여행번호';

ALTER TABLE travel
	AUTO_INCREMENT = 1;

-- 여행경로
CREATE TABLE travelRoute (
	travelRouteCode INT UNSIGNED NOT NULL COMMENT '경로번호', -- 경로번호
	travelCode      INT UNSIGNED NOT NULL COMMENT '여행번호', -- 여행번호
	lat             DOUBLE       NOT NULL COMMENT '위도', -- 위도
	lng             DOUBLE       NOT NULL COMMENT '경도', -- 경도
	location        VARCHAR(50)  NOT NULL COMMENT '장소명', -- 장소명
	locOrder        INT UNSIGNED NOT NULL COMMENT '장소순서' -- 장소순서
) ENGINE=InnoDB DEFAULT CHARSET=utf8
COMMENT '여행경로';

-- 여행경로
ALTER TABLE travelRoute
	ADD CONSTRAINT PK_travelRoute -- 여행경로 기본키
		PRIMARY KEY (
			travelRouteCode -- 경로번호
		);

ALTER TABLE travelRoute
	MODIFY COLUMN travelRouteCode INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '경로번호';

ALTER TABLE travelRoute
	AUTO_INCREMENT = 1;

-- 여행상세
CREATE TABLE travelDetail (
	travelCode INT UNSIGNED NOT NULL COMMENT '여행번호', -- 여행번호
	startDate  DATE         NOT NULL COMMENT '시작일', -- 시작일
	startTime  TIME         NULL     COMMENT '시작시간', -- 시작시간
	endDate    DATE         NOT NULL COMMENT '도착일', -- 도착일
	endTime    TIME         NULL     COMMENT '도착시간', -- 도착시간
	minPeople  INT UNSIGNED NOT NULL COMMENT '최소인원', -- 최소인원
	maxPeople  INT UNSIGNED NOT NULL COMMENT '최대인원' -- 최대인원
) ENGINE=InnoDB DEFAULT CHARSET=utf8
COMMENT '여행상세';

-- 여행상세
ALTER TABLE travelDetail
	ADD CONSTRAINT PK_travelDetail -- 여행상세 기본키
		PRIMARY KEY (
			travelCode -- 여행번호
		);

-- 여행이미지
CREATE TABLE travelImage (
	travelCode INT UNSIGNED NOT NULL COMMENT '여행번호', -- 여행번호
	image      TEXT         NOT NULL COMMENT '이미지' -- 이미지
) ENGINE=InnoDB DEFAULT CHARSET=utf8
COMMENT '작성한 글의 대표이미지';

-- 여행이미지
ALTER TABLE travelImage
	ADD CONSTRAINT PK_travelImage -- 여행이미지 기본키
		PRIMARY KEY (
			travelCode -- 여행번호
		);

-- 메세지
CREATE TABLE message (
	messageCode  INT UNSIGNED NOT NULL COMMENT '메세지번호', -- 메세지번호
	roomCode     INT UNSIGNED NOT NULL COMMENT '방번호', -- 방번호
	senderCode   INT UNSIGNED NOT NULL COMMENT '보내는회원번호', -- 보내는회원번호
	receiverCode INT UNSIGNED NOT NULL COMMENT '받는회원번호', -- 받는회원번호
	content      TEXT         NOT NULL COMMENT '메시지내용', -- 메시지내용
	sendDate     DATETIME     NOT NULL COMMENT '보낸날짜', -- 보낸날짜
	readFlag     TINYINT(1)   NOT NULL DEFAULT 0 COMMENT '읽음여부' -- 읽음여부
) ENGINE=InnoDB DEFAULT CHARSET=utf8
COMMENT '메세지';

-- 메세지
ALTER TABLE message
	ADD CONSTRAINT PK_message -- 메세지 기본키
		PRIMARY KEY (
			messageCode -- 메세지번호
		);

ALTER TABLE message
	MODIFY COLUMN messageCode INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '메세지번호';

ALTER TABLE message
	AUTO_INCREMENT = 1;

-- 평가
CREATE TABLE travelEval (
	evalCode  INT UNSIGNED NOT NULL COMMENT '평가번호', -- 평가번호
	applyCode INT UNSIGNED NOT NULL COMMENT '신청코드', -- 신청코드
	point     TINYINT(1) NOT NULL COMMENT '점수' -- 점수
) ENGINE=InnoDB DEFAULT CHARSET=utf8
COMMENT '평가';

-- 평가
ALTER TABLE travelEval
	ADD CONSTRAINT PK_travelEval -- 평가 기본키
		PRIMARY KEY (
			evalCode -- 평가번호
		);

ALTER TABLE travelEval
	MODIFY COLUMN evalCode INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '평가번호';

ALTER TABLE travelEval
	AUTO_INCREMENT = 1;

-- 후기
CREATE TABLE review (
	reviewCode INT UNSIGNED NOT NULL COMMENT '리뷰번호', -- 리뷰번호
	applyCode  INT UNSIGNED NOT NULL COMMENT '신청코드', -- 신청코드
	content    TEXT         NULL     COMMENT '내용', -- 내용
	writeTime  DATETIME     NOT NULL COMMENT '작성시간' -- 작성시간
) ENGINE=InnoDB DEFAULT CHARSET=utf8
COMMENT '후기';

-- 후기
ALTER TABLE review
	ADD CONSTRAINT PK_review -- 후기 기본키
		PRIMARY KEY (
			reviewCode -- 리뷰번호
		);

ALTER TABLE review
	MODIFY COLUMN reviewCode INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '리뷰번호';

ALTER TABLE review
	AUTO_INCREMENT = 1;

-- 신청관리
CREATE TABLE apply (
	applyCode  INT UNSIGNED NOT NULL COMMENT '신청코드', -- 신청코드
	travelCode INT UNSIGNED NOT NULL COMMENT '여행번호', -- 여행번호
	userCode   INT UNSIGNED NOT NULL COMMENT '신청회원번호' -- 신청회원번호
) ENGINE=InnoDB DEFAULT CHARSET=utf8
COMMENT '신청회원코드 수를 계산하여 신청인원 계산가능';

-- 신청관리
ALTER TABLE apply
	ADD CONSTRAINT PK_apply -- 신청관리 기본키
		PRIMARY KEY (
			applyCode -- 신청코드
		);

ALTER TABLE apply
	MODIFY COLUMN applyCode INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '신청코드';

ALTER TABLE apply
	AUTO_INCREMENT = 1;

-- 권한
CREATE TABLE authority (
	authority     VARCHAR(50) NOT NULL COMMENT '권한', -- 권한
	authorityName VARCHAR(50) NOT NULL COMMENT '권한이름' -- 권한이름
) ENGINE=InnoDB DEFAULT CHARSET=utf8
COMMENT '권한';

-- 권한
ALTER TABLE authority
	ADD CONSTRAINT PK_authority -- 권한 기본키
		PRIMARY KEY (
			authority -- 권한
		);

-- 보안리소스
CREATE TABLE securedResource (
	resourceCode    INT UNSIGNED NOT NULL COMMENT '리소스번호', -- 리소스번호
	resourcePattern VARCHAR(50)  NOT NULL COMMENT '리소스패턴', -- 리소스패턴
	sortOrder       INT(11)      NOT NULL COMMENT '정렬순서' -- 정렬순서
) ENGINE=InnoDB DEFAULT CHARSET=utf8
COMMENT '보안리소스';

-- 보안리소스
ALTER TABLE securedResource
	ADD CONSTRAINT PK_securedResource -- 보안리소스 기본키
		PRIMARY KEY (
			resourceCode -- 리소스번호
		);

ALTER TABLE securedResource
	MODIFY COLUMN resourceCode INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '리소스번호';

ALTER TABLE securedResource
	AUTO_INCREMENT = 1;

-- 보안리소스권한
CREATE TABLE securedResourceAuthority (
	resourceCode INT UNSIGNED NOT NULL COMMENT '리소스번호', -- 리소스번호
	authority    VARCHAR(50)  NOT NULL COMMENT '권한' -- 권한
) ENGINE=InnoDB DEFAULT CHARSET=utf8
COMMENT '보안리소스권한';

-- 회원권한
CREATE TABLE userAuthority (
	userCode  INT UNSIGNED NOT NULL COMMENT '회원번호', -- 회원번호
	authority VARCHAR(50)  NOT NULL COMMENT '권한' -- 권한
) ENGINE=InnoDB DEFAULT CHARSET=utf8
COMMENT '회원권한';

-- 언어
CREATE TABLE language (
	languageCode INT UNSIGNED NOT NULL COMMENT '언어번호', -- 언어번호
	language     VARCHAR(50)  NOT NULL DEFAULT 0 COMMENT '언어명' -- 언어명
) ENGINE=InnoDB DEFAULT CHARSET=utf8
COMMENT '언어';

-- 언어
ALTER TABLE language
	ADD CONSTRAINT PK_language -- 언어 기본키
		PRIMARY KEY (
			languageCode -- 언어번호
		);

ALTER TABLE language
	MODIFY COLUMN languageCode INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '언어번호';

ALTER TABLE language
	AUTO_INCREMENT = 1;

-- 회원언어
CREATE TABLE userLanguage (
	userCode     INT UNSIGNED NOT NULL COMMENT '회원번호', -- 회원번호
	languageCode INT UNSIGNED NOT NULL COMMENT '언어번호' -- 언어번호
) ENGINE=InnoDB DEFAULT CHARSET=utf8
COMMENT '회원언어';

-- 메시지룸
CREATE TABLE messageRoom (
	roomCode   INT UNSIGNED NOT NULL COMMENT '방번호', -- 방번호
	latestDate DATETIME     NOT NULL COMMENT '최근메시지시간' -- 최근메시지시간
) ENGINE=InnoDB DEFAULT CHARSET=utf8
COMMENT '메시지룸';

-- 메시지룸
ALTER TABLE messageRoom
	ADD CONSTRAINT PK_messageRoom -- 메시지룸 기본키
		PRIMARY KEY (
			roomCode -- 방번호
		);

ALTER TABLE messageRoom
	MODIFY COLUMN roomCode INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '방번호';

ALTER TABLE messageRoom
	AUTO_INCREMENT = 1;

-- 룸유저
CREATE TABLE roomUser (
	roomCode INT UNSIGNED NOT NULL COMMENT '방번호', -- 방번호
	userCode INT UNSIGNED NOT NULL COMMENT '회원번호' -- 회원번호
) ENGINE=InnoDB DEFAULT CHARSET=utf8
COMMENT '룸유저';

-- 룸유저
ALTER TABLE roomUser
	ADD CONSTRAINT PK_roomUser -- 룸유저 기본키
		PRIMARY KEY (
			roomCode, -- 방번호
			userCode  -- 회원번호
		);

-- 회원상세
ALTER TABLE userDetail
	ADD CONSTRAINT FK_user_TO_userDetail -- 회원 -> 회원상세
		FOREIGN KEY (
			userCode -- 회원번호
		)
		REFERENCES user ( -- 회원
			userCode -- 회원번호
		);

-- 여행
ALTER TABLE travel
	ADD CONSTRAINT FK_userDetail_TO_travel -- 회원상세 -> 여행
		FOREIGN KEY (
			userCode -- 작성회원번호
		)
		REFERENCES userDetail ( -- 회원상세
			userCode -- 회원번호
		);

-- 여행경로
ALTER TABLE travelRoute
	ADD CONSTRAINT FK_travel_TO_travelRoute -- 여행 -> 여행경로
		FOREIGN KEY (
			travelCode -- 여행번호
		)
		REFERENCES travel ( -- 여행
			travelCode -- 여행번호
		);

-- 여행상세
ALTER TABLE travelDetail
	ADD CONSTRAINT FK_travel_TO_travelDetail -- 여행 -> 여행상세
		FOREIGN KEY (
			travelCode -- 여행번호
		)
		REFERENCES travel ( -- 여행
			travelCode -- 여행번호
		);

-- 여행이미지
ALTER TABLE travelImage
	ADD CONSTRAINT FK_travel_TO_travelImage -- 여행 -> 여행이미지
		FOREIGN KEY (
			travelCode -- 여행번호
		)
		REFERENCES travel ( -- 여행
			travelCode -- 여행번호
		);

-- 메세지
ALTER TABLE message
	ADD CONSTRAINT FK_userDetail_TO_message -- 회원상세 -> 메세지
		FOREIGN KEY (
			senderCode -- 보내는회원번호
		)
		REFERENCES userDetail ( -- 회원상세
			userCode -- 회원번호
		);

-- 메세지
ALTER TABLE message
	ADD CONSTRAINT FK_userDetail_TO_message2 -- 회원상세 -> 메세지2
		FOREIGN KEY (
			receiverCode -- 받는회원번호
		)
		REFERENCES userDetail ( -- 회원상세
			userCode -- 회원번호
		);

-- 메세지
ALTER TABLE message
	ADD CONSTRAINT FK_messageRoom_TO_message -- 메시지룸 -> 메세지
		FOREIGN KEY (
			roomCode -- 방번호
		)
		REFERENCES messageRoom ( -- 메시지룸
			roomCode -- 방번호
		);

-- 평가
ALTER TABLE travelEval
	ADD CONSTRAINT FK_apply_TO_travelEval -- 신청관리 -> 평가
		FOREIGN KEY (
			applyCode -- 신청코드
		)
		REFERENCES apply ( -- 신청관리
			applyCode -- 신청코드
		);

-- 후기
ALTER TABLE review
	ADD CONSTRAINT FK_apply_TO_review -- 신청관리 -> 후기
		FOREIGN KEY (
			applyCode -- 신청코드
		)
		REFERENCES apply ( -- 신청관리
			applyCode -- 신청코드
		);

-- 신청관리
ALTER TABLE apply
	ADD CONSTRAINT FK_travel_TO_apply -- 여행 -> 신청관리
		FOREIGN KEY (
			travelCode -- 여행번호
		)
		REFERENCES travel ( -- 여행
			travelCode -- 여행번호
		);

-- 신청관리
ALTER TABLE apply
	ADD CONSTRAINT FK_userDetail_TO_apply -- 회원상세 -> 신청관리
		FOREIGN KEY (
			userCode -- 신청회원번호
		)
		REFERENCES userDetail ( -- 회원상세
			userCode -- 회원번호
		);

-- 보안리소스권한
ALTER TABLE securedResourceAuthority
	ADD CONSTRAINT FK_securedResource_TO_securedResourceAuthority -- 보안리소스 -> 보안리소스권한
		FOREIGN KEY (
			resourceCode -- 리소스번호
		)
		REFERENCES securedResource ( -- 보안리소스
			resourceCode -- 리소스번호
		);

-- 보안리소스권한
ALTER TABLE securedResourceAuthority
	ADD CONSTRAINT FK_authority_TO_securedResourceAuthority -- 권한 -> 보안리소스권한
		FOREIGN KEY (
			authority -- 권한
		)
		REFERENCES authority ( -- 권한
			authority -- 권한
		);

-- 회원권한
ALTER TABLE userAuthority
	ADD CONSTRAINT FK_authority_TO_userAuthority -- 권한 -> 회원권한
		FOREIGN KEY (
			authority -- 권한
		)
		REFERENCES authority ( -- 권한
			authority -- 권한
		);

-- 회원권한
ALTER TABLE userAuthority
	ADD CONSTRAINT FK_user_TO_userAuthority -- 회원 -> 회원권한
		FOREIGN KEY (
			userCode -- 회원번호
		)
		REFERENCES user ( -- 회원
			userCode -- 회원번호
		);

-- 회원언어
ALTER TABLE userLanguage
	ADD CONSTRAINT FK_user_TO_userLanguage -- 회원 -> 회원언어
		FOREIGN KEY (
			userCode -- 회원번호
		)
		REFERENCES user ( -- 회원
			userCode -- 회원번호
		);

-- 회원언어
ALTER TABLE userLanguage
	ADD CONSTRAINT FK_language_TO_userLanguage -- 언어 -> 회원언어
		FOREIGN KEY (
			languageCode -- 언어번호
		)
		REFERENCES language ( -- 언어
			languageCode -- 언어번호
		);

-- 룸유저
ALTER TABLE roomUser
	ADD CONSTRAINT FK_userDetail_TO_roomUser -- 회원상세 -> 룸유저
		FOREIGN KEY (
			userCode -- 회원번호
		)
		REFERENCES userDetail ( -- 회원상세
			userCode -- 회원번호
		);

-- 룸유저
ALTER TABLE roomUser
	ADD CONSTRAINT FK_messageRoom_TO_roomUser -- 메시지룸 -> 룸유저
		FOREIGN KEY (
			roomCode -- 방번호
		)
		REFERENCES messageRoom ( -- 메시지룸
			roomCode -- 방번호
		);
		
INSERT INTO authority (authority, authorityName) VALUES ('ROLE_ADMIN', '관리자');
INSERT INTO authority (authority, authorityName) VALUES ('ROLE_USER', '회원');

INSERT INTO language (languageCode, language) VALUES (1, 'korean');
INSERT INTO language (languageCode, language) VALUES (2, 'english');
INSERT INTO language (languageCode, language) VALUES (3, 'chinese');
INSERT INTO language (languageCode, language) VALUES (4, 'japanese');
INSERT INTO language (languageCode, language) VALUES (5, 'spanish');

INSERT INTO securedResource (resourceCode, resourcePattern, sortOrder) VALUES (1, '/signIn', 20);
INSERT INTO securedResource (resourceCode, resourcePattern, sortOrder) VALUES (2, '/travelList', 30);
INSERT INTO securedResource (resourceCode, resourcePattern, sortOrder) VALUES (3, '/readTravel', 40);
INSERT INTO securedResource (resourceCode, resourcePattern, sortOrder) VALUES (4, '/writeTravel', 50);
INSERT INTO securedResource (resourceCode, resourcePattern, sortOrder) VALUES (5, '/adminPage', 60);

INSERT INTO securedResourceAuthority (resourceCode, authority) VALUES (2, 'ROLE_USER');
INSERT INTO securedResourceAuthority (resourceCode, authority) VALUES (3, 'ROLE_USER');
INSERT INTO securedResourceAuthority (resourceCode, authority) VALUES (4, 'ROLE_USER');
INSERT INTO securedResourceAuthority (resourceCode, authority) VALUES (5, 'ROLE_ADMIN');