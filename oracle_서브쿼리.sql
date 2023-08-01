--각자 계정접속한다.

DROP TABLE subemp;
--emp 테이블 생성
Create Table subemp(
	emp_id 	   int, --사원번호
	emp_name	   Varchar2(20) Not Null,  --사원이름
	job      	               Varchar2(20) Not Null,  --직업
	dept_id  	  int, --부서번호
	sal 	              int Not Null,--급여
	bonus	              int,--보너스
	mgr_id	              int,--관리자번호
	hiredate             date  Not Null, --입사일
	constraint emp_id_pk22 Primary Key(emp_id) --제약조건
);

select * from subemp;

--데이터삽입

INSERT INTO subEMP VALUES (6200,'박철수','대표',200,  5000,NULL,NULL, '1997-12-17');

INSERT INTO subEMP VALUES (6311,'김대정','부장',100, 3500,Null,6200, '1998-12-17');

INSERT INTO subEMP VALUES (7489,'민동규','세일즈',400,  1850,200,6321, '1999-02-27') ;

INSERT INTO subEMP VALUES (7522,'정종철','세일즈',400, 1850,300,6321,'1998-02-28') ;

INSERT INTO subEMP VALUES (6321,'이종길','부장',400, 3800,500,6200,'1999-04-20') ;

INSERT INTO subEMP VALUES (6351,'진대준','부장',300,   2850,NULL,6200,'2000-05-31');

INSERT INTO subEMP VALUES (7910,'이영희','경리',300, 1000,NULL,6351,'2001-05-01');

INSERT INTO subEMP VALUES (6361,'김철수','부장',200, 3200,NULL,6200,'2000-06-09') ;

INSERT INTO subEMP VALUES (7878,'백기수','연구직',200, 3000,NULL,6361,'2001-06-05') ;

INSERT INTO subEMP VALUES (7854,'진영진','세일즈',400, 1500,0,6321,'2001-09-08') ;

INSERT INTO subEMP VALUES (7872,'이문정','사무직',100, 1500,NULL,6311,'2001-02-12') ;

INSERT INTO subEMP VALUES (7920,'마리아','사무직',300, 1050,NULL,6351,'2001-03-18');

INSERT INTO subEMP VALUES (7901,'정동길','연구직',NULL, 3000,NULL,NULL,'2001-12-03');

INSERT INTO subEMP VALUES (7933,'김철수','사무직',200,  1050,NULL,6361,'2002-01-02');
--emp테이블 생성 / 레코드 추가

DROP TABLE subdept;

SELECT * FROM subdept;
CREATE TABLE subdept(dept_id int, dname VARCHAR(20) );
INSERT INTO subdept VALUES(100,'관리부');
INSERT INTO subdept VALUES(200,'경리부');
INSERT INTO subdept VALUES(300,'생산부');
INSERT INTO subdept VALUES(400,'영업부');

SELECT *FROM subemp;
SELECT *FROM subdept;

------------------------------------------------------

ex1) 이종길 사원의 부서명(dname)을 검색하시오.
select dname
from subdept
where dept_id = (select dept_id from subemp where emp_name = '이종길');

-- join -- error
select dname
from subemp join subdept
Using (dept_id)
where subemp.emp_name = '이종길';

ex2) dept_id가 100인 사원급여의 최대값보다 많이 받는 사원을 검색하시오.
select *
from subemp
where sal > (select max(sal) from subemp where dept_id = 100);

ex3)급여를 3000이상 받는 사원이 소속된 부서와 
   동일한 부서에서 근무하는 사원들의 정보
select *
from subemp
where dept_id in (select distinct dept_id from subemp where sal >= 3000 and dept_id is not null)
order by dept_id;
 
ex4) 부서번호가 300인 사원들중에서 급여를 가장 많이 받는 사원보다
      더 많은 급여를 받는 사람의 정보를 검색.
select *
from subemp
where sal > (select max(sal) from subemp where dept_id = 300);

   
ex5) 부서번호가 300인 사원들중에서 급여를 가장 적게 받는 사원보다
     더 많은 급여를 받는 사람의 정보를 검색
select *
from subemp
where sal > (select min(sal) from subemp where dept_id = 300);
   
ex6)정동길의 급여와 동일 하거나 더 많이 받는 사원의 정보검색
select *
from subemp
where sal >= (select sal from subemp where emp_name = '정동길');

ex7)직급이 사무직인 사원의 부서번호와 부서명 출력
select dept_id, dname
from subdept
where dept_id in (select dept_id from subemp where job = '사무직');

ex8) 부서가 경리부인 모든 사원의 정보출력
select *
from subemp
where dept_id = (select dept_id from subdept where dname = '경리부');

ex9)대표이사에게 보고를 하는 모든 사원의 정보출력
select *
from subemp
where mgr_id = (select emp_id from subemp where job = '대표');

ex10) 이름에 '정'이 들어가면서 평균급여보다 높은 급여를 받는 -- 다시풀기
     사원과 동일한 부서에서 근무하는 사원의 정보 검색.
      (단, 부서번호 null은 제외함)
      
select *
from subemp
where dept_id in (select dept_id from subemp 
                  where emp_name like '%정%' and sal > (select AVG(sal) from subemp) 
                  and dept_id is not null); 
    
      
ex11) 각 부서의 어떤 평균 급여보다 급여를 많이 -- 다시풀어보기
      받는 사원의 정보를 검색

select *
from subemp
where sal > (select max(avg(sal)) from subemp where dept_id is not null group by dept_id); 

ex12)  모든 사무직 사원보다 급여가 적으면서 사무직이 -- 다시 
 아닌 모든 사원의 정보검색
 
select *
from subemp
where sal < (select min(sal) from subemp where job = '사무직') and job != '사무직';


