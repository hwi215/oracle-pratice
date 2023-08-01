create table MEMBER(
    ID VARCHAR2(20) CONSTRAINT MEMBER_ID_PK PRIMARY KEY,
    NAME VARCHAR2(10) NOT null, -- 반드시 값이 있어야 한다.
    JUMIN char(13),
    age NUMBER(2),
    ADDR VARCHAR2(10 CHAR),
    REG_DATE DATE 
);
 

-- 테이블 검색
select * from MEMBER;


-- 테이블 구조 
DESC member;

-- 레코드 등록

--INSERT INTO 테이블이름(컬럼명, 컬럼명, ,,,,) VALUES(값, 값, 값 ,,,)
--INSERT INTO VALUES(값, 값, 값 ,,,) -- 순서대로 값 넣을


INSERT INTO MEMBER VALUES('hwi', 'hwikyung', '111', 20, '서울시강남구', SYSDATE);
INSERT INTO MEMBER (ID, NAME) VALUES('kim', 'hwi215');

select * from member;

INSERT INTO MEMBER (ID, NAME) VALUES('kim', 'hwi215');

insert into MEMBER (ID, NAME, JUMIN, AGE, ADDR, REG_DATE) values ('hello', 'hwiii', '222', 25, 'buc', '2023-5-5');

-- char vs varchar2 비교 
-- insert into MEMBER (ID, NAME, JUMIN) values('FF', 'nameee', 수고했어');


-- 10byte vs 10charChar


-- varchar 검색 
select * from  MEMBER where JUMIN = '111';

----------------------------------------------------------

-- 두개의 컬럼을 하나로 묶어서 PK를 설정해보자 => 복합키!

-- 기존 테이블 삭제
drop table emp;

select * from MEMBER;


-- id와 jimin 컬럼을 하나로 묶어서 PK로 만들고싶다.
create table MEMBER(
    ID VARCHAR2(20) ,
    NAME VARCHAR2(10),
    JUMIN char(13),
    CONSTRAINT MEMBER_ID_JUMIN_PK PRIMARY KEY(ID, JUMIN)  
     
);

desc MEMBER;

insert into MEMBER(ID, JUMIN) values('kim2', '1111');
insert into MEMBER(ID) values('kim');


-------부서테이블 ---------------------------
create table DEPT(
DEPT_CODE char(3) CONSTRAINT defp_code_pk primary key,
DNAME varchar2(30) not null,
Loc varchar2(50) 
);

-- 레코드 추가 
insert into DEPT values('A01', 'urdflqn2', '서울2');
insert into DEPT values('A02', 'urdflqn2', '서울2');
insert into DEPT values('A03', 'urdflqn3', '서울3');

select * from tab;


-- 사원테이블 작성 - DEPT_CODE : 부서코드 
create table EMP(
  EMP_NO NUMBER(3)  CONSTRAINT EMP_NO_PK primary key,
  ENAME varchar2(15),
  SAL NUMBER(5),
  DEPT_CODE char(3) CONSTRAINT emp_code_FK references dept(dept_code), -- 부서코드
  HIREDATE DATE default SYSDATE 
);
 
-- pk 테스트
insert into EMP values(100, 'hwi', 1, 'A01', default);
insert into EMP(emp_no, ename, dept_code) values(200, '하이', 'A02'); -- 중복가능 
insert into EMP(emp_no, ename, dept_code) values(300, '하이', null); -- 중복가능 

select * from tab;

-- 삭제(부모키를 삭제해보자) 
commit;
select * from MEMBER;
delete from MEMBER; -- 레코드 삭제(rollback 가능)
rollback;
truncate table MEMBER; -- 레코드 절삭(rollback 안됨)
rollback;

delete from DEPT WHERE DEPT_CODE = 'A02';


delete from EMP where DEPT_CODE = 'A01';

-- fk의 옵션을 설정해서 테이블을 생성해보자

drop table emp;
drop table dept;

-- on delete cascade(참조되고 있는 자식이 있어도 같이 삭제되어버림) vs on delete set null
create table EMP(
  EMP_NO NUMBER(3)  CONSTRAINT EMP_NO_PK primary key,
  ENAME varchar2(15) NOT NULL,
  SAL NUMBER(5),
  DEPT_CODE char(3) CONSTRAINT emp_code_FK 
  references dept(dept_code) on delete cascade, -- 부서코드
  HIREDATE DATE default SYSDATE 
);

insert into EMP values(100, 'hwi', 1, 'A01', default);
insert into EMP(emp_no, ename, dept_code) values(200, '하이', 'A02'); -- 중복가능 
insert into EMP(emp_no, ename, dept_code) values(300, '하이', null); -- 중복가능 

-- 참조되고 있는 부모키를 삭제해보자 
--delete from DEPT whrere DEPT_CODE = 'A01';

-- on delete set null인 경우 
create table EMP(
  EMP_NO NUMBER(3)  CONSTRAINT EMP_NO_PK primary key,
  ENAME varchar2(15) NOT NULL,
  SAL NUMBER(5),
  DEPT_CODE char(3) CONSTRAINT emp_code_FK 
  references dept(dept_code) on delete set null, -- 부서코드
  HIREDATE DATE default SYSDATE 
);

-- 삭제
delete from DEPT where DEPT_CODE = 'A01';
select * from emp;

-- fk 제약조건을 마지막에,,,

drop table emp;
create table EMP(
  EMP_NO NUMBER(3)  CONSTRAINT EMP_NO_PK primary key,
  ENAME varchar2(15) NOT NULL,
  SAL NUMBER(5),
  DEPT_CODE char(3), -- 부서코드
  HIREDATE DATE default SYSDATE ,
  CONSTRAINT emp_code_FK foreign key(DEPT_CODE) -- 뒤로 제약조건 뺄땐, foreign 추가!
  references dept(dept_code) on delete set null
);

select * from test;


----------------------------
create table test(
  id varchar2(10) primary key,
  jumin char(13) not null unique,
  name varchar2(10) unique,
  age number(2) check(age >= 20 and age <= 30), -- check(조건)
  gender char(3) check(gender = '남' or gender = '여'), 
  reg_date date default sysdate not null -- default를 not null보다 먼저 작성하자! 

);

// insert로 테스트해보기 
insert into test(id, jumin, gender) values(11, '하남이', '하'); 
insert into test(id, jumin, name, age, gender) values(10, '부천시', '하이2', 25, '여');   

-- test table에 alter를 이용해서 수정해보자.
select * from test;
-- 테이블 수정

-- 1) 컬럼추가 
alter table test add (addr varchar2(10));
-- 2) 컬럼삭제
alter table test drop column age;
-- 3) datatype 변경
alter table test modify addr number;
insert into test(id, jumin, addr) values(100, '광주', 10);

-- 4) 컬럼이름 변경
alter table test rename column addr to count;

-- 5) 제약조건 추가 ?
--alter table test add constraint test_FK;



---------------scott
select * from emp;

-- 원본데이터 복사(emp 테이블을 복사함)
create table copy_emp
as select * from emp;

select * from copy_emp;

-- 테이블에 특정 컬럼과 특정 레코드만 복사하자
create table copy_emp3
as select empno, ename, sal from emp where sal > 1500;
select * from copy_emp3;

-- 테이블의 구조만 복사하고 싶다
create table copy_emp33
as select * from emp where 1 = 0;

select * from copy_emp33;
-------------------------------------
select * from copy_emp;

-- enpno가 7499인 사원의 job을 teacher, ename을 hwi로 변경하자

update copy_emp
set job = 'teacher', ename = 'hwi', sal = 5000
where empno=7499;


create user ws0727 identified by ws0727;
grant connect, resource, dba to ws0727;
show user;
conn ws0727/ws0727;


create table user_dto
(
  user_seq number constraint user_seq_pk primary key,
  user_name varchar2(12),
  email varchar2(20) constraint email_uq unique(email) not null,
  phone varchar2(14),
  is_sleep varchar2(10) default 'N'
);


 
---------------------DB 2일차 -----------------------------------------------------------------------------
select * from emp;


/*
   SELECT문장 - DQL문장
    : 구조
    select distinct | * | 컬럼명 as 별칭, 컬럼명 별칭,....   : 열을 제한 :PROJECTION
    from 테이블이름     
    [where 조건식 ]  : 레코드(튜플)제한  - SELECTION
    [order by 컬럼명 desc | asc , .. ] -정렬
    
    
    * distinct 는 중복레코드를 제거
    * AS 는 컬럼에 별칭 만들기 
    * 실행순서
      SELECT   3) 
      FROM     1)
      WHERE    2) 
      ORDER BY 4) 
    
*/

--EX) SCOTT계정 접속 

SELECT * FROM EMP; --사원테이블
SELECT * FROM DEPT;--부서정보테이블

--1) EMP테이블에서 원하는 컬럼(별칭)
select empno as num, ename as naem, job as job
from emp;

--2) 중복행 제거하기 - DISTINCT
 --EX) 우리회사에 어떤 JOB있는지 JOB의 종류를 알고싶다!!!
select distinct job
from emp;

 
--3) 조건 만들기 
 -- 급여가 3000이상인 사원 검색
select * 
from emp
where sal >= 3000;
 
 --4) 정렬
 -- 급여가 2000이상인 사원을 검색하고 급여를 기준으로 정렬***
select *
from emp
where sal >= 2000
order by sal asc;


 --JOB을 기준으로 내림차순정렬하고 JOB이 같으면 급여를 기준으로 정렬

select *
from emp
order by job desc, sal asc;

--칼럼들끼리 연산이 가능하다
 



-- NULL값을 다른 값으로 변경해서 연산 할 수 있다  -->  NVL(칼럼명, 변경값

  
--님 년봉은 ~입니다. 출력 ---  문자열 연결 || 이용



  
-----------------------------------------------------------------------------------
/*
  연산자 종류
  1) 산술연산자
     +, -, *, / 
     나머지 : MOD(값, 나눌수)
     
   2) 관계연산자
       > , <, >= , <= , !=, <>
       같다  :  =
       
   3) 비교연산자
    - AND
    - OR
    - IN :  컬럼명 IN (값, 값, 값)  - 하나의 컬럼을 대상으로 또는으로 비교할때 사용한다.
    
    - BETWEEN AND :  컬럼명 BETWEEN 최소 AND 최대 - 하나의 컬럼을 대상으로 최소 ~ 최대를 비교할때
    
    - LIKE  : 와일드카드 문자와 함께 사용한다.
        1. % : 0개이상의 문자
        2. _ : 한글자  
        
        EX)  name like 'J%' ;   - NAME에 첫글자가 J로 시작하는 모든 문자
             name like '___' ;  - NAME이 3글자 
             name like 'J_J%';  - NAME의 첫글자가 J로 시작하고 3번째 글자 A인 정보 검색
             
    
    - NOT : 위의 모든 연산자들 앞에 NOT을 붙히면 반대 개념.
        
*/
--EX) 산술연산자 : EMP에서 년봉계산 = (SAL + COMM) *12  해서 년봉 컬럼 
 
 
 -- * NVL(값, 대치값)  : NULL을 찾아 대치값으로 변경한다. 


--EX) 년봉을 계산하기 위해서 COMM의 NULL을 찾아 0으로 변경한후 연산한다. - NVL함수 사용


--EX) ~님의 년봉은 ~ 입니다. 출력  : 문자열을 연결할때 || 사용한다. concat



--EX) SAL 가 2000 ~ 4000사원 검색(AND, BETWEEN AND )
select * 
from emp
where sal >= 2000 and sal <= 4000;

select * 
from emp
where sal between 2000 and 4000;


--EX) SAL 가 2000 ~ 4000사원아닌 레코드 검색 -  NOT
select * 
from emp
where not (sal >= 2000 and sal <= 4000);

select * 
from emp
where sal not between 2000 and 4000;


--EX) EMPNO 가 7566, 7782,7844인 사원검색 ( OR, IN)
select * 
from emp
where empno in (7566, 7782, 7844);


--EX) EMPNO 가 7566, 7782,7844인 사원이 아닌 검색 ( NOT)
select * 
from emp
where empno not in (7566, 7782, 7844);

---------------------------------------------------------------------------
--1) JOB에 'A' 문자로시작하는 레코드 검색
select * 
from emp
where job like 'A%';

--2) JOB에 끝 끌자가 'N'으로 끝나는 레코드 검색
select * 
from emp
where job like '%N';



--3) ENAME이 4글자인 레코드 검색

select * 
from emp
where ename like '____';

--4) ENAME에 A글자가 포함된 레코드 검색

select * 
from emp
where ename like '%A%';

select *
from emp
where lower(job) like lower('a%'); -- 대소문자 구분 없이 검색하기 **

--5) ENAME전체 글자가 5글자이고 두번째 글자가 m이면서끝글자가 h인 레코드 검색

select * 
from emp
where ename like '_____' and ename like '_M__H';

select * 
from emp
where upper(ename) like '_____' and ename like upper('_M__H'); -- 대소문자 구분 없이 select


select * 
from member
where name like '%%%'; -- 전체 다 나옴(null 제외) 


select * 
from member
where name like '%%%'escape '@'; -- 전체 다 나옴(null 제외)  -> 문자열로 인
식

-------------------------------------------------------------------------------------------------


/*
    NULL 찾기
    1) IS NULL
    2) IS NOT NULL
*/

-- COMM이 NULL인 레코드 검색 

--COPY_EMP 테이블에서 COMM이 NULL레코드를 COMM의 값을 100으로 변경
-- 업데이트

select * 
from member


h update copy_mmp***
set comn = nvl(comm, 100); -- 성능의 이슈가 있을 수 이,(***


 
-- NULL이 있는 컬럼을 대상으로 정렬을 해보자
SELECT * FROM EMP ORDER BY COMM; -- 오름차순일때는 NULL은 마지막에 조회된다
SELECT * FROM EMP ORDER BY COMM DESC; -- 내름차순일때는 NULL은 처음에 조회된다 
SELECT * FROM EMP ORDER BY COMM ASC NULLS FIRST; --NULL을 우선적으로 출력


-------------- 내 계정으로...
select * from tab;
select * from MEMBER;

insert into MEMBER(id, name, jumin) values('hwi', 'hwik%yung','111');
insert into MEMBER(id, name, jumin) values('kim', 'hello','300');
insert into MEMBER(id, name, jumin) values('kyung', 'hwik%yung','111');
insert into MEMBER(id, name, jumin) values('kang', 'hwik%yung','111');




select * 
from member
where comn is not null;
 
 
 select * from copy_emp;
 select * from emp;
 
select * 
from emp 
order bt com;

select * 
from emp 
order bt com desc;

select * 
from emp 
order bt asc null ;

-- null의 우선순위 


----------------------------------------------------------주제2. 함수----------------------------------



  문자열 함수
    - upper(문자열) => 모두 대문자
    - lower(문자열) => 모두 소문자
    - initcap(문자열)=> 단어의 첫 글자 대문자로 표현
    
    - length(문자열) => 문자열의 길이
    
    - substr(문자열, 시작, 개수) => 문자의 일부분 추출
       EX) substr(문자열, INDEX) : 문자열에서 INDEX 부터 끝까지 추출
           substr(문자열, INDEX, 개수) : 문자열에서 INDEX 부터 개수 까지 추출
           
           * INDEX는 1 부터 시작한다.
           
           
    - instr(문자열, 찾을문자열) => 찾을 문자열의 출현 위치(INDEX) 알려줌
    - instr(문자열, 찾을문자열, 시작번지수, 몇번째 출현)
       ex)instr(job,'A') => JOB에서 왼쪽부터 A 가 처음 출현하는 위치를 알려준다.
          instr(job,'A' , 시작번지수) => JOB에서 왼쪽에서 시작번지수 부터 A 가 처음 출현하는 위치를 알려준다.
          instr(job,'A' , -1) => JOB에서 오른쪽부터 A 가 처음 출현하는 위치를 알려준다.
          
          instr(job,'A' ,3,  2) => 
          
          * 만약, 찾는 문자열이 없으면 0 이다. 
          
    - lpad(문자열, 전체자리수, 특정문자) 
     => 오른쪽 정렬 후 왼쪽 빈 공백에 특정문자로 채움
     
    - Rpad(문자열, 전체자리수, 특정문자) 
     => 왼쪽 정렬 후 오른쪽 빈 공백에 특정문자로 채움)
     
    - ltrim() => 왼쪽 공백제거
    - rtrim()=> 오른쪽 공백제거
 */
 

SELECT ENAME ,UPPER(ENAME), LOWER(ENAME), INITCAP(ENAME), LENGTH(ENAME), LENGTHB(ENAME)
  FROM EMP;


-- SUBSTR(문자열, 시작, 개수) 문자열에서 시작부터 개수만큼 문자열 추출, 개수 생략하면 시작부터 끝까지 추출
SELECT JOB, SUBSTR(JOB, 1, 3) ,SUBSTR(JOB, 2, 3), SUBSTR(JOB, 3)
  FROM EMP;
  
  
-- SUBSTR(문자열, 시작, 개수): 시작을 0으로 하면 그냥 1로 본다. 음수를 주면 오른쪽 부터
SELECT JOB, SUBSTR(JOB, 0, 3), SUBSTR(JOB, -2, 3)
  FROM EMP;



-- INSTR(문자열, 문자, 시작, 몇번째 출현): 문자열에서 두번째인수문자가 몇 번째에 있는 지 찾아주는 함수
SELECT 'ABCDE ABCDE ABCDE ABCDE'
       ,INSTR('ABCDE ABCDE ABCDE ABCDE', 'C')
       ,INSTR('ABCDE ABCDE ABCDE ABCDE','C', 5)
       ,INSTR('ABCDE ABCDE ABCDE ABCDE','C', 5, 2)  --두번째에 오는 
  FROM DUAL;
  
  
   
  
-- ex) 이메일 주소에 @전까지 출력, @이후부터 출력 


select 이메일, substr(이메일, 1, instr(이메일, '@')-1) as 전, substr(이메일, instr(이메일, '@')+1) as domain
from teacher;

SELECT * FROM teacher;

 /*
   숫자 관련 함수
   - round(숫자, 자리수)=> 반올림
   
   - ceil(숫자) => 올림 한 후 정수반환
   - floor(숫자)=>내림 한 후 정수 반환
   
   - mod(숫자, 나눌수) => 나머지
   - trunc(숫자, 자리수)=> 버림
 */
  
-- ROUND
SELECT 231.45136, ROUND(231.45136,2), ROUND(231.45136 , -2), ROUND(231.45136, 0) 
FROM DUAL;
  
-- TRUNC
SELECT 231.45136, TRUNC(231.45136,2), TRUNC(231.45136 , -2), TRUNC(231.45136, 0)
  FROM DUAL;
  
-- CEIL, FLOOR - 결과 정수 반환(자리수 설정 없음)
SELECT 231.45136, CEIL(231.45136), FLOOR(231.45136)
  FROM DUAL;
  
-- 나머지 
SELECT SAL, MOD(SAL, 2)
  FROM EMP;
  

/*
   날짜 함수
   - sysdate => 현재날짜와 시간** 가장 많이씀 
   - months_between(날짜, 날짜) => 두 날짜 사이이의 개월 수를 구함
   - add_months(날짜, 숫자) => 날짜에서 숫자만큼 월을 더함.
   
   - next_day(날짜, 요일) => 날짜에서 가장 가까운 요일의 날짜구함
       (1 = 일요일 , 2 = 월요일,..... )
       
   - last_day(날짜) => 날짜 달의 마지막 날짜를 구함
   
   - EXTRACT : 년, 월, 일 등 날짜데이터 뽑기 
   - TO_CHAR : 형변
*/

SELECT EMPNO, HIREDATE, floor(SYSDATE-HIREDATE), HIREDATE+5, TO_CHAR(HIREDATE + 5/24, 'YYYY-MM-DD HH:MI:SS')
  FROM EMP;
  

SELECT SYSDATE, EXTRACT(DAY FROM SYSDATE), EXTRACT(MONTH FROM SYSDATE), EXTRACT(YEAR FROM SYSDATE)
 FROM DUAL;
  
  
SELECT SYSDATE, TO_CHAR(SYSDATE, 'DD'), TO_CHAR(SYSDATE, 'MM'), TO_CHAR(SYSDATE, 'YYYY')
  FROM DUAL;

-- 입사일 -> 2002년 10월 2일 
SELECT HIREDATE, TO_CHAR(HIREDATE, 'YYYY') || '년', CONCAT(TO_CHAR(HIREDATE, 'MM'), '월')
  FROM EMP;


SELECT HIREDATE, TO_CHAR(HIREDATE, 'YYYY"년"MM"월"DD"일"')
  FROM EMP;


-- 기간
SELECT TRUNC(SYSDATE - TO_DATE('2023-3-2')) AS 만난일수
  FROM DUAL;
 
 
 
-- 100일
SELECT TO_DATE('2023-3-2') + 100
  FROM DUAL;

setset to pr



/*데이터타입 변환

 TO_CHAR()
 TO_DATE()
 TO_NUMBER()
   - to_char(datetime, format형식)=> 날짜를 format형식으로 변환
   
     ex) to_char(sysdate , 'YYYY-MM-DD')
         to_char(sysdate , 'YYYY-MM-DD HH:MI:SS')
         to_char(sysdate , 'YYYY-MM-DD HH24:MI:SS')
         
    - to_char(number, format형식)=> 숫자를 format형식으로 변환
     ex) 3자로 콤마
     to_cahr(2000, '999,999') => 2,000
     to_cahr(2000, 'L999,999') => \2,000 => 지역에 따른 화페표시
     to_cahr(2000, '$999,999') => $2,000 
     
    - to_date(문자열) => 문자를 날짜로 변환
    
    - to_number(문자열)=> 문자를 숫자로 변환

 */




------------------------------------------------
 --NVL2함수  ex) MGR이 NULL이 아니면 두번째 인수를, NULL이면 세번째인수 리턴
 
SELECT EMPNO, ENAME, MGR  ,NVL2(MGR, MGR||'는 관리자','최고관리자')
  FROM EMP;

 
SELECT EMPNO, JOB, NULLIF(JOB, 'MANAGER')  FROM EMP;


--COALESCE함수 ; 가장 먼저 NULL이 아닌 것을 반환
SELECT ENAME, COMM, SAL, COALESCE(COMM, SAL, 50) RESULT
  FROM EMP;
  
  -- 추가
  -- ull은 첫번째 인수와 두번째 인수가 같으면 NULL, 아니면 첫번째인수
select * from emp;

---------------==============================================


/*
  SELECT절에서 조건에 따라 실행문장을 다르게 할수 있도록 하는 함수
   1) decode(대상, 값, 문장, 값, 문장, 값, 문장,,,,,)
        => 대상에 해당하는 값이 일치하는 경우 사용함
        
   2) case [대상]
        when 조건1 then 문장
        when 조건2 then 문장
        when 조건3 then 문장
        ...
        else 문장
     end
 */
 
--EX) 성적테이블에서 국어점수가 80이상이면 합격, 아니면 불합격  합격여부 필드를 만든다. - CASE END 로사용


  /*EX)성적테이블에서 BAN이 1이면 'MAS과정', 2이면 'IOT과정', 
  3이면 'DESIGN과정' 이외는 'FULL STACK과정' 라는 과정명 필드를
 만든다.*/
 
 
 /*
EX) EMP테이블에서 DEPNO가 10 이면 관리부, 20이면 총무부, 30이면 영업부 
     이외의 값은 기타부 로 출력하고 컬럼명은 부서명 으로 한다.
     (DECODE, CASE END)
     DECODE는 값이 정확하게 일치해야한다
*/
     
    
    /*
    ex)job이 manager인 경우 sal*0.1, ANALYST 인경우는   sal *0.2
         SALESMAN인 경우는 sal * 0.3을 구해서 성과급 필드를 만든다.
          (case end, decode 다 해본다) 
          */
          

select empno, ename job, 
      case upper(job)
      when 'manager' then sal * 0.1
      when 'ANALYST' then sal * 0.2
      when 'SALESMAN' then sal * 0.3
      end
      as 성과급
from emp;


/*
          
    ex) sal이 2000이하이면 '저소득층'
      sal이 2001 ~ 4000사이면 '중산층'
      sal이 4001 이상이면 '고소득층'  구하여 등급 별칭 해준다.
      (case end) 
*/
 
 --select empno, ename, job, sal, deptno,
--decode(depth, 10, '관리부', 20, '총무부' 30, '영업부, 기타부);
 


select ename, job, decode(job, 'manager', sal * 0.1, 'ANALYST', sal * 0.2, 'ALESMAN', sal * 0.3) as 성과급
from emp; 


select empno, ename job, 
      case  
      when sla <= 2000 then '저소득총'
      when sla < 5000 then '중산층'
      when sla < 5000 than '고소득층'
      end
      as 성과급
from emp;
 
 
 
-------------------------------------------------------------------------

/*
  집계함수
     - sum(컬럼명) => 합계
     - avg(컬럼명) => 평균(null값은 제외하고 나눔)
     - max(컬럼명) => 최대값
     - min(컬럼명) => 최소값
     
     - count(컬럼명) => 총 레코드수(null값은 제외함)
     - count(*) => null을 포함한 총 레코드수
     
     - rank(expr) within group(order by 컬럼명 asc | desc )
        => 전체 값을 대상으로 각 값의 순위를 구함.
     ex) --급여가 3000의 등수 구하기
        SELECT RANK(3000) within GROUP(ORDER BY sal desc) FROM EMP
        
        
    *** 집계함수 = 그룹함수를 사용할때 일반 컬럼을 select 절과 함께 사용안된다 => group by 필요!!
*/

-----------------------------------------------------
--집계함수

CREATE TABLE REPORT(
 NAME VARCHAR2(20) CONSTRAINT REPORT_NAME_PK PRIMARY KEY,
 BAN CHAR(1),
 KOR NUMBER(3) CHECK(KOR BETWEEN 0 AND 100),
 ENG NUMBER(3) CHECK(ENG BETWEEN 0 AND 100),
 MATH NUMBER(3) CHECK(MATH BETWEEN 0 AND 100)
);

SELECT * FROM REPORT;

--샘플레코드
INSERT INTO REPORT VALUES('희정', 1 , 80, 70,90);
INSERT INTO REPORT VALUES('효리', 1 , 90, 50,90);

INSERT INTO REPORT VALUES('나영', 2 , 100, 65,85);
INSERT INTO REPORT VALUES('재석', 2 , 80, 70, 95);
INSERT INTO REPORT VALUES('희선', 2 , 85, 45,80);

INSERT INTO REPORT VALUES('승기', 3 , 50, 70,70);
INSERT INTO REPORT VALUES('중기', 3 , 90, 75,80);
INSERT INTO REPORT VALUES('혜교', 3 , 70, 90,95);
INSERT INTO REPORT VALUES('미나', 3 , NULL, 80,80);


-- 개인별 국어총점, 국어평균을 검색해보자.
select name, ban, kor, eng, math, (nvl(kor, 0) + nvl(eng,0) + nvl(math, 0)) as 총점,
floor((nvl(kor, 0) + nvl(eng, 0) + nvl(math, 0)) / 3) as 평균
from report;

-- 각 반별 국어 최대, 최소, 학생수 평균, 총점  
select max(kor) as 최대, min(kor) as 최소, count(*) as 전체학생, floor(nvl(avg(kor), 0)), sum(kor)
from report 
group by ban;    -- group by절에 나온 컬럼은 select절에서 사용할 

-- 국어점수의 최대, 최소, 전체학생수를 검색해보자.
select name, max(kor) as 최대, min(kor) as 최소, count(*) as 전체학생
from report
group by kor;
  

--수학점수 최대, 최소, 학생수 
select max(math) as 최대, min(math) as 최소, count(*) as 전체학생
from report
group by math;   

--국어점수의 총점, 평균, NULL을 0으로 변경해서 평균 검색해보자 - AVG()함수는 NULL을 제외한 레코드수로 평균을 구한다. 



--반별 국어 최대, 최소 총점 평균 인원수 - GROUP BY절에 나온 컬럼은 SELECT절에 집계함수와 함게 사용가능

-- KOR의 점수가 70이상인 학생들의 반별 국어 최대, 최소 총점 평균 인원수
-- -> 집계함수를 where에서 조건으로 사용할 수 없다.
select max(kor) as 국어최대, min(kor) as 국어최소 
from report
group by ban
having avg(kor) > 70;

-- KOR의 평균이 80 이상인 학생들의 반별 국어 최대, 최소 총점 평균 인원수 



/*
  중요!!
 SELECT   5)
 FROM     1)
 WHERE    2)
 GROUP BY 3)
 HAVING   4)
 ORDER BY 6)

*/



-- rollup -> 테이블 맨 마지막에 전체 토탈을 같이 뽑아줌  : rollup(A, B) : A를 기준으로 -> 2개 이상의 컬럼을 그룹핑할때 사용!!  
-----------------------------------------------
SELECT BAN , SUM(KOR) 총점
FROM REPORT
WHERE KOR >=70
GROUP BY rollup(BAN); -- 소계 + 총계


SELECT BAN , SUM(KOR) 총점
FROM REPORT
WHERE KOR >=70
GROUP BY CUBE(BAN);

SELECT BAN , SUM(KOR) 총점
FROM REPORT
WHERE KOR > 70
GROUP BY GROUPING SETS(BAN);


---------------------------------------
/*
 ROLLUP VS CUBE VS GROUPING SETS
*/
CREATE TABLE MONTHLY_SALES( --월별매출
  GOODS_ID VARCHAR2(5), --상품아이디
  MONTH VARCHAR2(10), -- 월
  COMPANY VARCHAR2(20), --회사
  SALES_AMOUNT NUMBER -- 매출금액
);

select * from MONTHLY_SALES;

INSERT INTO MONTHLY_SALES VALUES('P01','2023-01', '롯데', 15000);
INSERT INTO MONTHLY_SALES VALUES('P01','2023-02', '롯데', 25000);

INSERT INTO MONTHLY_SALES VALUES('P02','2023-01', '삼성', 8000);
INSERT INTO MONTHLY_SALES VALUES('P02','2023-02', '삼성', 12000);


INSERT INTO MONTHLY_SALES VALUES('P03','2023-01', 'LG', 8500);
INSERT INTO MONTHLY_SALES VALUES('P03','2023-02', 'LG', 13000);

SELECT * FROM MONTHLY_SALES;

SELECT GOODS_ID , SUM(SALES_AMOUNT)
FROM MONTHLY_SALES
GROUP BY ROLLUP(GOODS_ID); -- 총계 함께 출력 

SELECT MONTH , SUM(SALES_AMOUNT)
FROM MONTHLY_SALES
GROUP BY ROLLUP(MONTH);


SELECT GOODS_iD, MONTH , SUM(SALES_AMOUNT) 총매출액
FROM MONTHLY_SALES
GROUP BY ROLLUP(GOODS_iD,MONTH); -- ROLLUP 첫번째 나온 컬럼을 기준으로 소계, 전체 (인수의 순서가 중요) -> 첫번째 인자의 값이 없으면 그거에 대한 그룹화는 안해줌 ( 첫번째 인자에는 Null 없음)

SELECT MONTH , GOODS_iD  , SUM(SALES_AMOUNT) 총매출액
FROM MONTHLY_SALES
GROUP BY ROLLUP(MONTH , GOODS_iD);

--CUBE -> cube보다는 rollup을 더 권장함  
SELECT GOODS_iD, MONTH , SUM(SALES_AMOUNT) 총매출액
FROM MONTHLY_SALES
GROUP BY CUBE(GOODS_iD,MONTH); -- CUBE 소계부분을 각 컬럼을 기준으로 나오기때문에서 인수의 순서가 상관없다. -> 첫번째 인자의 값이 없어도 그룹화 해줌 (첫번째 인자에 null도 있음)  

SELECT  MONTH , GOODS_ID, SUM(SALES_AMOUNT) 총매출액
FROM MONTHLY_SALES
GROUP BY CUBE(MONTH , GOODS_ID);


--GROUPING SETS -> 간단하게 나옴 
SELECT GOODS_iD, MONTH , SUM(SALES_AMOUNT) 총매출액
FROM MONTHLY_SALES
GROUP BY GROUPING SETS(GOODS_iD,MONTH);

SELECT  MONTH , GOODS_ID, SUM(SALES_AMOUNT) 총매출액
FROM MONTHLY_SALES
GROUP BY GROUPING SETS(MONTH ,GOODS_iD);

                '

 















