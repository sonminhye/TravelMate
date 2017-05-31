-- 회원
CREATE TABLE user (
	userCode  INT UNSIGNED NOT NULL COMMENT '회원코드', -- 회원코드
	id        VARCHAR(100) NOT NULL COMMENT '아이디', -- 아이디
	password  VARCHAR(20)  NOT NULL COMMENT '비밀번호', -- 비밀번호
	enabled   TINYINT(1)   NOT NULL DEFAULT 1 COMMENT '사용여부', -- 사용여부
	authority VARCHAR(20)  NULL     COMMENT '권한' -- 권한
)
COMMENT '회원';

-- 회원
ALTER TABLE user
	ADD CONSTRAINT PK_user -- 회원 기본키
		PRIMARY KEY (
			userCode -- 회원코드
		);

ALTER TABLE user
	MODIFY COLUMN userCode INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '회원코드';

ALTER TABLE user
	AUTO_INCREMENT = 1;

-- 회원상세
CREATE TABLE userDetail (
	userCode  INT UNSIGNED NOT NULL COMMENT '회원코드', -- 회원코드
	name      VARCHAR(12)  NOT NULL COMMENT '이름', -- 이름
	age       INT UNSIGNED NOT NULL COMMENT '나이', -- 나이
	sex       CHAR(6)      NOT NULL COMMENT '성별', -- 성별
	location  VARCHAR(20)  NOT NULL COMMENT '지역', -- 지역
	meanPoint FLOAT(3,2)   NOT NULL DEFAULT 0 COMMENT '평점' -- 평점
)
COMMENT '회원상세';

-- 회원상세
ALTER TABLE userDetail
	ADD CONSTRAINT PK_userDetail -- 회원상세 기본키
		PRIMARY KEY (
			userCode -- 회원코드
		);

-- 여행
CREATE TABLE travel (
	travelCode INT UNSIGNED NOT NULL COMMENT '여행번호', -- 여행번호
	userCode   INT UNSIGNED NOT NULL COMMENT '작성회원코드', -- 작성회원코드
	title      VARCHAR(20)  NOT NULL COMMENT '제목', -- 제목
	content    TEXT         NULL     COMMENT '내용', -- 내용
	writeDate  DATE         NOT NULL COMMENT '작성일' -- 작성일
)
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

-- 언어
CREATE TABLE languageList (
	userCode INT UNSIGNED NOT NULL COMMENT '회원코드', -- 회원코드
	ableLang VARCHAR(20)  NOT NULL COMMENT '언어명' -- 언어명
)
COMMENT '언어';

-- 언어
ALTER TABLE languageList
	ADD CONSTRAINT PK_languageList -- 언어 기본키
		PRIMARY KEY (
			userCode, -- 회원코드
			ableLang  -- 언어명
		);

-- 여행경로
CREATE TABLE travelRoute (
	travelCode INT UNSIGNED NOT NULL COMMENT '여행번호', -- 여행번호
	lat        DOUBLE       NOT NULL COMMENT '위도', -- 위도
	lng        DOUBLE       NOT NULL COMMENT '경도', -- 경도
	location   VARCHAR(20)  NOT NULL COMMENT '장소명' -- 장소명
)
COMMENT '여행경로';

-- 여행경로
ALTER TABLE travelRoute
	ADD CONSTRAINT PK_travelRoute -- 여행경로 기본키
		PRIMARY KEY (
			travelCode, -- 여행번호
			lat,        -- 위도
			lng         -- 경도
		);

-- 여행상세
CREATE TABLE travelDetail (
	travelCode INT UNSIGNED NOT NULL COMMENT '여행번호', -- 여행번호
	startDate  DATE         NOT NULL COMMENT '시작일', -- 시작일
	startTime  TIME         NULL     COMMENT '시작시간', -- 시작시간
	endDate    DATE         NOT NULL COMMENT '도착일', -- 도착일
	endTime    TIME         NULL     COMMENT '도착시간', -- 도착시간
	minPeople  INT UNSIGNED NOT NULL COMMENT '최소인원', -- 최소인원
	maxPeople  INT UNSIGNED NOT NULL COMMENT '최대인원' -- 최대인원
)
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
)
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
	senderCode   INT UNSIGNED NOT NULL COMMENT '보내는회원코드', -- 보내는회원코드
	receiverCode INT UNSIGNED NOT NULL COMMENT '받는회원코드', -- 받는회원코드
	content      TEXT         NOT NULL COMMENT '메시지내용', -- 메시지내용
	sendDate     DATETIME     NOT NULL COMMENT '보낸날짜', -- 보낸날짜
	readFlag     TINYINT(1)   NOT NULL DEFAULT 0 COMMENT '읽음여부' -- 읽음여부
)
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
	travelCode INT UNSIGNED NOT NULL COMMENT '여행번호', -- 여행번호
	userCode   INT UNSIGNED NOT NULL COMMENT '평가회원코드', -- 평가회원코드
	point      INT UNSIGNED NOT NULL COMMENT '점수' -- 점수
)
COMMENT '평가';

-- 평가
ALTER TABLE travelEval
	ADD CONSTRAINT PK_travelEval -- 평가 기본키
		PRIMARY KEY (
			travelCode, -- 여행번호
			userCode    -- 평가회원코드
		);

-- 후기
CREATE TABLE review (
	travelCode INT UNSIGNED NOT NULL COMMENT '여행번호', -- 여행번호
	userCode   INT UNSIGNED NOT NULL COMMENT '신청회원코드', -- 신청회원코드
	content    TEXT         NULL     COMMENT '내용', -- 내용
	writeTime  DATETIME     NOT NULL COMMENT '작성시간' -- 작성시간
)
COMMENT '후기';

-- 후기
ALTER TABLE review
	ADD CONSTRAINT PK_review -- 후기 기본키
		PRIMARY KEY (
			travelCode, -- 여행번호
			userCode    -- 신청회원코드
		);

-- 신청관리
CREATE TABLE apply (
	travelCode INT UNSIGNED NOT NULL COMMENT '여행번호', -- 여행번호
	userCode   INT UNSIGNED NOT NULL COMMENT '신청회원코드' -- 신청회원코드
)
COMMENT '신청회원코드 수를 계산하여 신청인원 계산가능';

-- 신청관리
ALTER TABLE apply
	ADD CONSTRAINT PK_apply -- 신청관리 기본키
		PRIMARY KEY (
			travelCode, -- 여행번호
			userCode    -- 신청회원코드
		);

-- 회원상세
ALTER TABLE userDetail
	ADD CONSTRAINT FK_user_TO_userDetail -- 회원 -> 회원상세
		FOREIGN KEY (
			userCode -- 회원코드
		)
		REFERENCES user ( -- 회원
			userCode -- 회원코드
		);

-- 여행
ALTER TABLE travel
	ADD CONSTRAINT FK_userDetail_TO_travel -- 회원상세 -> 여행
		FOREIGN KEY (
			userCode -- 작성회원코드
		)
		REFERENCES userDetail ( -- 회원상세
			userCode -- 회원코드
		);

-- 언어
ALTER TABLE languageList
	ADD CONSTRAINT FK_user_TO_languageList -- 회원 -> 언어
		FOREIGN KEY (
			userCode -- 회원코드
		)
		REFERENCES user ( -- 회원
			userCode -- 회원코드
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
			senderCode -- 보내는회원코드
		)
		REFERENCES userDetail ( -- 회원상세
			userCode -- 회원코드
		);

-- 메세지
ALTER TABLE message
	ADD CONSTRAINT FK_userDetail_TO_message2 -- 회원상세 -> 메세지2
		FOREIGN KEY (
			receiverCode -- 받는회원코드
		)
		REFERENCES userDetail ( -- 회원상세
			userCode -- 회원코드
		);

-- 평가
ALTER TABLE travelEval
	ADD CONSTRAINT FK_apply_TO_travelEval -- 신청관리 -> 평가
		FOREIGN KEY (
			travelCode, -- 여행번호
			userCode    -- 평가회원코드
		)
		REFERENCES apply ( -- 신청관리
			travelCode, -- 여행번호
			userCode    -- 신청회원코드
		);

-- 후기
ALTER TABLE review
	ADD CONSTRAINT FK_apply_TO_review -- 신청관리 -> 후기
		FOREIGN KEY (
			travelCode, -- 여행번호
			userCode    -- 신청회원코드
		)
		REFERENCES apply ( -- 신청관리
			travelCode, -- 여행번호
			userCode    -- 신청회원코드
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
			userCode -- 신청회원코드
		)
		REFERENCES userDetail ( -- 회원상세
			userCode -- 회원코드
		);