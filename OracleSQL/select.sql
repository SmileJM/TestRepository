/*
SELECT ��
*/

-- bwriter �� '��'�� ��� �÷� ��������
select *
from board
where bwriter='��';

-- bwriter �� '��'�� bno, btitle, bwriter ��������
select bno, btitle, bwriter
from board
where bwriter='��';

-- bno �� 1~10 �� �Խù� ��������
select bno, btitle, bwriter
from board
where bno>=1 and bno<=10;

select bno, btitle, bwriter
from board
where bno between 1 and 10;
-- bwriter �� '��'�� �����ϴ� �Խù��� bno, btitle, bwriter ��������
select bno, btitle, bwriter
from board
where bwriter like '%��%';
-- �Խù��� ���� �߿� '�ڹ�'�� ���ԵǾ� �ִ� �Խù��� ��ȣ, ����, �۾��� ��������
select bno, btitle, bwriter
from board
where btitle like '%�ڹ�%';
-- �Խù��� ���� �Ǵ� ���� �߿� '�ڹ�'�� ���ԵǾ� �ִ� �Խù��� ��ȣ, ����, �۾���, ���밡������
select bno, btitle, bwriter, bcontent
from board
where btitle like '%�ڹ�%' or  bcontent like '%�ڹ�%';
-- �۾��̰� ���ڹ�, ��浿, ������� �Խù��ǹ�ȣ, ����, �۾��� ��������
select bno, btitle, bwriter
from board
where bwriter = '���ڹ�' or bwriter = '��浿' or bwriter = '�����';

select bno, btitle, bwriter
from board
where bwriter in('���ڹ�', '��浿', '�����');

-- ÷�� ������ ���� �Խù��� ��������
select *
from board
where BORIGINALFILENAME is null;
-- �Խù��� �ۼ��� ����� �̸��� ��������(�ߺ� ����)
select distinct bwriter
from board;

-- �Խù��� ��¥�� 2016���� �Խñ� ��������
select *
from board
--where '2016.01.01' <= bdate and '2016.12.31' >= bdate;
 where bdate between '2016.01.01'and '2016.12.31';
-- where bdate <= to_date('16/12/31', 'YY/MM/DD');

-- bno > ��ȣ, btitle > ����, bwriter > �۾��̷� �÷� �̸� �����ؼ� ��������
select bno as ��ȣ, btitle as ����, bwriter as �۾���
from board;

/*
  ����
*/
-- �Խù��� ��ȣ�� �������� ������������ ��������
select *
from board
order by bno asc;

-- 2017�⵵�� �ۼ��� �Խù��� ��ȣ�� �������� ������������ ��������
select * 
from board
where bdate between '2017.01.01' and '2017.12.31'
order by bno asc;

-- �۾��̸� �������� 1�� ����, �ۼ����� ���� 2������
select * 
from board
order by bwriter asc, bdate desc;

/*
����¡ ó��
*/
-- ����Ǿ� �ִ� ������� �� ��ȣ �ű��
select rownum, bno, btitle, bwriter, bdate, bhitcount
from board;
-- ���� �� ���ȣ �ű��
select rownum, bno, btitle, bwriter, bdate, bhitcount
from (select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc);
-- Ư�� �� ��ȣ ���� ��������(Top N�� ��������, ���������� N��)
select *
from (
  select rownum as r, bno, btitle, bwriter, bdate, bhitcount
  from (select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc)
  where rownum<=(7*15)
)
where r >= ((7-1)*15+1);
-- ���� ���ȣ �� ���ȣ ������ �Խù� ��������

-- ��ü ��� ���ϱ�
select count(*)from board;
select count(bno)from board;
select count(BORIGINALFILENAME)from board;
select count(*)from board where bpassword <> 12345;

select * 
from (
  select rownum as r, mid, mname, mage, maddress
  from (select mid, mname, mage, maddress from member)
  where rownum <=20
)
where r >= 11;
