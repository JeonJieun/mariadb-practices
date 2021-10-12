-- 서브쿼리(SUBQUERY) SQL 문제입니다.

-- 문제1. 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(*)
from salaries
where to_date = '9999-01-01'
and salary > (select avg(salary) 
				from salaries
			   where to_date = '9999-01-01');

-- 문제2. 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다.
select a.emp_no as '사번', concat(a.first_name, ' ', a.last_name) as '이름', c.dept_name as '부서', d.salary as '연봉'
from employees a, dept_emp b, departments c, salaries d, (select c.dept_no, max(b.salary) as max_salary
															from employees a, salaries b, dept_emp c
														   where a.emp_no = b.emp_no
															 and a.emp_no = c.emp_no
															 and b.to_date = '9999-01-01'
															 and c.to_date = '9999-01-01'
														group by c.dept_no) e
where a.emp_no = b.emp_no
and a.emp_no = d.emp_no
and b.dept_no = e.dept_no
and d.salary = e.max_salary
and c.dept_no = e.dept_no
and d.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
order by d.salary desc;

-- 문제3. 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 
select a.emp_no as '사번', concat(a.first_name, ' ', a.last_name) as '이름', c.salary as '연봉'
from employees a, dept_emp b, salaries c, (select c.dept_no, avg(b.salary) as avg_salary
											 from employees a, salaries b, dept_emp c
											where a.emp_no = b.emp_no
											  and a.emp_no = c.emp_no
											  and b.to_date = '9999-01-01'
											  and c.to_date = '9999-01-01'
										 group by c.dept_no) d
where a.emp_no = b.emp_no
and a.emp_no = c.emp_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
and c.salary > d.avg_salary
and b.dept_no = d.dept_no;

-- 문제4. 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
select a.emp_no as '사번', concat(a.first_name, ' ', a.last_name) as '이름', concat(d.first_name, ' ', d.last_name) as '매니저 이름', e.dept_name as '부서'
from employees a, dept_emp b, dept_manager c, employees d, departments e
where a.emp_no = b.emp_no
and b.dept_no = c.dept_no
and c.emp_no = d.emp_no
and c.dept_no = e.dept_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01';

-- 문제5. 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.
select a.emp_no, concat(a.first_name, ' ', a.last_name) as '이름', b.title as '직책', c.salary as '연봉'
from employees a, titles b, salaries c, dept_emp d
where a.emp_no = b.emp_no
and a.emp_no = c.emp_no
and a.emp_no = d.emp_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
and d.to_date = '9999-01-01'
and d.dept_no = (select dept_no
				   from (select dept_no, avg(salary) as avg_salary
						   from salaries a, dept_emp b
						  where a.emp_no = b.emp_no
							and a.to_date = '9999-01-01'
							and b.to_date = '9999-01-01'
					   group by dept_no
					   order by avg_salary desc
						  limit 0 , 1) a)
order by c.salary desc;

-- 문제6. 평균 연봉이 가장 높은 부서는? 
select d.dept_name, round(avg(b.salary)) as avg_salary
from employees a, salaries b, dept_emp c, departments d
where a.emp_no = b.emp_no
and a.emp_no = c.emp_no
and c.dept_no = d.dept_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
group by c.dept_no
having avg_salary = (select max(avg_salary)
					   from (select round(avg(b.salary)) as avg_salary
					   from employees a, salaries b, dept_emp c
					  where a.emp_no = b.emp_no
						and a.emp_no = c.emp_no
						and b.to_date = '9999-01-01'
						and c.to_date = '9999-01-01'
				   group by c.dept_no) a);
        
-- 문제7. 평균 연봉이 가장 높은 직책?
select c.title, round(avg(b.salary)) as avg_salary
from employees a, salaries b, titles c
where a.emp_no = b.emp_no
and a.emp_no = c.emp_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
group by c.title
having avg_salary = (select max(avg_salary)
					   from (select rount(avg(b.salary)) as avg_salary
							   from employees a, salaries b, titles c
							  where a.emp_no = b.emp_no
								and a.emp_no = c.emp_no
								and b.to_date = '9999-01-01'
								and c.to_date = '9999-01-01'
						   group by c.title) a);
        
-- 문제8. 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은? 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.
select f.dept_name as '부서이름', a.first_name as '사원이름', d.salary as '연봉', g.first_name as '매니저 이름', e.salary as '매니저 연봉'
from employees a, dept_emp b, dept_manager c, salaries d, salaries e, departments f, employees g
where a.emp_no = b.emp_no
and c.dept_no = b.dept_no
and a.emp_no = d.emp_no
and c.emp_no = e.emp_no
and c.dept_no = f.dept_no
and c.emp_no = g.emp_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
and d.to_date = '9999-01-01'
and e.to_date = '9999-01-01'
and d.salary > e.salary;
