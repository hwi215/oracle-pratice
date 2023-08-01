--SCOTT접속 

SELECT * FROM emp;
SELECT * FROM dept; --부서정보
SELECT * FROM salgrade; --급여등급


-- 1. SMITH 에 대한  정보 검색(ename, emp.deptno, loc .)
select ename, emp.deptno, loc
from emp join dept
on emp.DEPTNO = dept.deptno
where emp.ename = 'SMITH';

--2. NEW YORK에 근무하는 사원의 이름과 급여를 출력
select ename, sal
from emp join dept 
on emp.deptno = dept.deptno
where dept.loc = 'NEW YORK';

-- 3. ACCOUNTING 부서 소속 사원의 이름과 입사일 출력 -> 서브쿼리로도 해보기 
select ename, hiredate
from emp join dept 
on emp.deptno = dept.deptno
where dept.dname = 'ACCOUNTING';

-- 4. 직급이 MANAGER인 사원의 이름, 부서명 출력z
select ename, dname
from emp join dept 
on emp.deptno = dept.deptno
where emp.job = 'MANAGER';

-- 5. 사원의 급여가 몇 등급인지를 검색
-- between A and B
select * from salgrade;
select * from emp;

SELECT EMPNO, ENAME, SAL, GRADE, LOSAL, HISAL
 FROM EMP JOIN salgrade
 ON SAL BETWEEN LOSAL AND HISAL;
  


--6. 사원 테이블의 부서 번호로 부서 테이블을 참조해서 부서명, 급여 등급도 검색**
SELECT DNAME, GRADE
FROM EMP JOIN dept
using(EMPNO) join salgrade
where emp.deptno = dept.deptno and SAL BETWEEN LOSAL AND HISAL;
  

--7. SMITH의 메니저(mgr) 이름(ename) 검색
select ename
from emp join emp
on emp.mgr = emp.deptno
where emp.ename = 'SMITH';


--8. 관리자가 KING인 사원들의 이름과 직급(job) 검색
select ename, job
from emp join dept
on emp.deptno = dept.deptno
where emp.ename = 'KING';

--9. SMITH 와 동일한 부서번호(DEPTNO)에서 근무하는 사원의 이름 출력
-- 단, SMITH 데이터 절대 출력 불가
select e2.ename
from emp e1 join emp e2
on e1.deptno = e2.deptno
where e1.ename = 'SMITH'  and e2.ename != 'SMITH';

10. SMITH 와 동일한 근무지(LOC)에서 근무하는 사원의 이름 출력       --- 어려움 , 서브쿼리도 가능 
-- 단, SMITH 데이터 절대 출력 불가
select e2.ename
from emp e1 join emp e2
on e1.deptno = e2.deptno
where e1.ename = 'SMITH' and e2.ename != 'SMITH'; 

-- 11, 사원명, 해당 하는 메니저명 검색 -- left join  
-- 반드시 모든 사원들(CEO포함) 정보 검색
-- CEO인 경우 메니저 정보 null
select e1.ename,e2.ename 
from emp e1 left join emp e2
on e1.MGR = e2.EMPNO;

 
