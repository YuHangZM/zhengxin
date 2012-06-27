create or replace procedure initializtion as

  --------------------------------����������õ��ı���-------------------------
  v_emplyname          tb_emply_mng.emplyname%type; --ְ������
  v_emplyacc           tb_emply_mng.emplyacc%type; --ְ���˺�
  v_unitacc            tb_emply_mng.unitacc%type; --��λ�˺�
  v_emplyid            tb_emply_mng.emplyid%type; --���֤��
  v_accblnce           tb_emply_mng.accblnce%type; --�˻����
  v_unitname           tb_unit_mng.unitname%type; --��λ����
  v_monthpay           tb_emply_mng.unithndinamnt%type; -- �½ɽ��
  v_emply_st           char(20); --ְ��״̬
  v_periodorgblnce     tb_emply_mng.periodorgblnce%type; --�������
  v_curbalance         tb_emply_mng.periodorgblnce%type; --�������
  v_emplyhndinamnt     tb_emply_mng.emplyhndinamnt%type; --���и��˲���
  v_enddate            char(6); --�ϴνɴ�����
  v_nextpaymoney       tb_emply_mng.unithndinamnt%type; --�´��½ɴ��
  v_nextemplyhndinamnt tb_emply_mng.emplyhndinamnt%type; --���и��˲���
  v_opendate           char(8); --��������
  v_nextpaymonth       char(6); --�´λ���·�
  v_lastbalance        tb_emplydetailacc_stat.accblnce%type; --�������
  v_lastinterest       tb_emplydetailacc_stat.accblnce%type; --������Ϣ
  v_cur_monthpay       lmk_gjjye.cur_monthpay%type; --�����ɺϼ�
  v_cur_addpay         lmk_gjjye.cur_addpay%type; --���겹�ɺϼ�
  v_cur_pickmoney      lmk_gjjye.cur_pickmoney%type; --����֧ȡ�ϼ�
  v_cur_month          char(2); --ϵͳ�·�
  v_interestyear_start char(8); --��Ϣ��ȿ�ʼ
  v_interestyear_end   char(8); --��Ϣ��Ƚ���
  v_biztype            char(10); --ҵ������
  v_temp_money         lmk_gjjye.cur_pickmoney%type; --�ϼƽ��
  v_emplydetail_id     tb_emplydetailacc_stat.emplydetail_id%type; --�����Ϣid
  v_thisyearsum_num    number; --�ϼƵ��������ж��Ƿ���ڹ鼯ҵ��
  v_gjjmx_num          number; --��������ϸ����
  v_dkmx_num           number; --������ϸ����
  -------------------------------��������ϸ�õ��ı���----------------------------
  v_happendate    char(8); --����,
  v_operationtype char(20); --ժҪ,
  v_loanamnt      tb_emplydetailacc_stat.loanamnt%type; --����,
  v_lendamnt      tb_emplydetailacc_stat.lendamnt%type; --�跽,
  v_accblnce_mx   tb_emplydetailacc_stat.accblnce%type; --���,
  v_hndindate     char(6); --�������
  v_occurmoney    tb_emplydetailacc_stat.loanamnt%type; --������
  v_cdflag        char(4);
  -------------------------------��������õ��ı���-------------------------------
  v_loanaccnum   number; --�����˺Ÿ���
  v_temp_loanacc lmk_dkye.loan_kou_acc%type; --������Ĵ����˺�
  --------------------------------������ϸ�õ��ı���------------------------------
  v_operationdate char(8); --����
  v_loan_type     char(20); --ժҪ
  v_backcorpus    tb_loan_emplydetailacc_stat.rovercorpus%type; --���ձ���
  v_backinterest  tb_loan_emplydetailacc_stat.rshouldpayinterest%type; --������Ϣ
  v_realpay       tb_loan_emplydetailacc_stat.realpay%type; --������
  v_amnt          tb_loan_emplydetailacc_stat.loan_amnt%type; --ʱ��������

  --------------------------------   �����������  --------------------------------
  Cursor cr_rq001_empId_orgid is
    select e.emplyacc  zgzh, --ְ���˺�
           e.unitacc   dwzh, --��λ�˺�
           e.emplyname zgxm, --ְ������
           e.emplyid   sfzh --���֤��
      from tb_emply_mng e, tb_unit_mng u, rq001 r
     where e.unitacc = u.unitacc
       and r.name = e.emplyname
       and r.card_num = e.emplyid
       and r.empid = e.emplyacc;

  --�˻���Ϣ�α�
  Cursor cr_gjjye_1 is
    select e.accblnce, --�˻����
           u.unitname, --��λ����
           e.unithndinamnt + e.emplyhndinamnt, -- �½ɽ��
           e.emply_st, --ְ��״̬
           e.periodorgblnce, --�������
           e.accblnce - e.periodorgblnce, --�������
           e.emplyhndinamnt, --���и��˲���
           to_char(to_date(u.enddate, 'yyyy-mm'), 'yyyymm'), --�ϴνɴ�����
           e.unithndinamnt + e.emplyhndinamnt, --�´��½ɴ��
           e.emplyhndinamnt, --���и��˲���
           to_char(e.opendate, 'yyyymmdd'), --��������
           to_char(add_months(to_date(u.enddate, 'yyyy-mm'), 1), 'yyyymm') --�´λ���·�
      from tb_emply_mng e, tb_unit_mng u
     where e.unitacc = u.unitacc
       and e.emplyacc = v_emplyacc
       and e.unitacc = v_unitacc;

  --����ϼ��α�
  Cursor cr_gjjye_2 is
    select sum(es.loanamnt+es.lendamnt), es.operationtype
      from tb_emplydetailacc_stat es
     where es.operationtype in (0, 3, 5, 6)
       and es.happendate between
           to_date(v_interestyear_start, 'yyyy-mm-dd') and
           to_date(v_interestyear_end, 'yyyy-mm-dd')
       and es.emplyacc = v_emplyacc
       and es.unitacc = v_unitacc
     group by es.operationtype;

  --��������ϸ��ѯ
  Cursor cr_gjjmx is
    select to_char(t_start.happendate, 'yyyymmdd'), --����,
           t_start.operationtype, --ժҪ,
           t_start.loanamnt, --����,
           t_start.lendamnt, --�跽,
           t_start.accblnce, --���,
           to_char(to_date(h_tran.hndindate, 'yyyy_mm'), 'yyyymm') --�������
      from tb_emplydetailacc_stat t_start, tb_hndin_tran h_tran
     where t_start.crdncenum = h_tran.hndincrdncenum(+)
       and t_start.emplyacc = v_emplyacc
       and t_start.unitacc = v_unitacc
     order by t_start.emplydetail_id asc;

  --������ϸ��ѯ
  Cursor cr_dkhsmx is
    select to_char(t_loan_start.operationdate, 'yyyymmdd'), --����,
           t_loan_start.loan_type, --ժҪ,
           t_loan_start.rovercorpus + t_loan_start.rthistermcorpus, -- ���ձ���,
           t_loan_start.rshouldpayinterest + t_loan_start.rthisterminterest +
           t_loan_start.roverinterest, -- ������Ϣ,
           t_loan_start.realpay, -- ������,
           t_loan_start.loan_amnt -- ʱ��������
      from tb_loan_emplydetailacc_stat t_loan_start
     where t_loan_start.loan_type != 0
       and t_loan_start.contract_code = v_temp_loanacc
       and t_loan_start.realpay <> 0
     order by t_loan_start.emplydetail_id;

begin
  open cr_rq001_empId_orgid;
  loop
    fetch cr_rq001_empId_orgid
      into v_emplyacc, v_unitacc, v_emplyname, v_emplyid;
    exit when cr_rq001_empId_orgid%notfound;
  
    --��ѯǩԼ��ְ����ź͵�λ��ţ�����ѭ��������4�ű�lmk_gjjye,lmk_gjjmx,lmk_dkye,lmk_dkhsmx----------
    delete from lmk_gjjye gjjye_t
     where gjjye_t.empid = v_emplyacc
       and gjjye_t.orgid = v_unitacc; -------------ɾ�������������и�ְ����Ϣ
    delete from lmk_gjjmx gjjmx_m
     where gjjmx_m.empid = v_emplyacc
       and gjjmx_m.orgid = v_unitacc; --------------ɾ����������ϸ���и�ְ����Ϣ
  
    select count(b.contract_code)
      into v_loanaccnum
      from tb_borrower_info b, tb_loan_info l
     where b.contract_code = l.contract_code
       and b.borrower_name = v_emplyname
       and b.borrower_id = v_emplyid; -------------��ְ���Ƿ����
  
    if v_loanaccnum > 0 then
      select tt.code
        into v_temp_loanacc
        from (select b.contract_code code,
                     row_number() over(order by l.leave_corpus desc) mm
                from tb_borrower_info b, tb_loan_info l
               where b.contract_code = l.contract_code
                 and b.borrower_name = v_emplyname
                 and b.borrower_id = v_emplyid) tt
       where mm = 1; ----------------��ѯ��ְ������������Ĵ����˺�
    
      delete from lmk_dkye dkye_y
       where dkye_y.loan_kou_acc = v_temp_loanacc; --------------ɾ�����������и�ְ������������ļ�¼
      delete from lmk_dkhsmx dkhsmx_m
       where dkhsmx_m.loan_kou_acc = v_temp_loanacc; --------------ɾ�����������ϸ�и�ְ������������ļ�¼
    end if;
  
    -------------------------------��������ʼ---------------------------------------------------------
    open cr_gjjye_1;
    fetch cr_gjjye_1
      into v_accblnce, --�˻����
    v_unitname, --��λ����
    v_monthpay, -- �½ɽ��
    v_emply_st, --ְ��״̬
    v_periodorgblnce, --�������
    v_curbalance, --�������
    v_emplyhndinamnt, --���и��˲���
    v_enddate, --�ϴνɴ�����
    v_nextpaymoney, --�´��½ɴ��
    v_nextemplyhndinamnt, --���и��˲���
    v_opendate, --��������
    v_nextpaymonth --�´λ���·�
    ;
    exit when cr_gjjye_1%notfound;
    close cr_gjjye_1;
    ------------------------------------ְ��״̬ת����ʼ-------------------------------------------------    
    if v_emply_st = '0' then
      v_emply_st := '����';
    end if;
    if v_emply_st = '1' then
      v_emply_st := '���';
    end if;
    if v_emply_st = '2' then
      v_emply_st := '����';
    end if;
    if v_emply_st = '3' then
      v_emply_st := '����';
    end if;
    if v_emply_st = '4' then
      v_emply_st := '�ʽ�ת��';
    end if;
    if v_emply_st = '5' then
      v_emply_st := '�ʽ�ת��';
    end if;
    if v_emply_st = '6' then
      v_emply_st := '������ȡ';
    end if;
    if v_emply_st = '7' then
      v_emply_st := 'ɾ��';
    end if;
    if v_emply_st = '8' then
      v_emply_st := '����';
    end if;
    ------------------------------------ְ��״̬ת������-------------------------------------------------
    ------------------------------------��ѯ������������Ϣ��ʼ---------------------------------------
  
    v_lastbalance  := 0;
    v_lastinterest := 0;
    select max(s.emplydetail_id)
      into v_emplydetail_id
      from tb_emplydetailacc_stat s
     where s.emplyacc = v_emplyacc
       and s.unitacc = v_unitacc
       and s.operationtype = 10;
    if v_emplydetail_id is not null then
      select s.loanamnt
        into v_lastbalance
        from tb_emplydetailacc_stat s
       where s.emplydetail_id = v_emplydetail_id;
      select s.accblnce - s.loanamnt
        into v_lastinterest
        from tb_emplydetailacc_stat s
       where s.emplydetail_id = v_emplydetail_id;
    end if;
  
    ------------------------------------��ѯ������������Ϣ����---------------------------------------
    ------------------------------------ ȡ��Ϣ��ȿ�ʼ -------------------------------------------------
    select to_char(sysdate, 'mm') into v_cur_month from dual;
    if v_cur_month > '06' then
      select to_char(sysdate, 'yyyy') || '0701'
        into v_interestyear_start
        from dual;
      select to_char(sysdate, 'yyyy') + 1 || '0630'
        into v_interestyear_end
        from dual;
    else
      select to_char(sysdate, 'yyyy') - 1 || '0701'
        into v_interestyear_start
        from dual;
      select to_char(sysdate, 'yyyy') || '0630'
        into v_interestyear_end
        from dual;
    end if;
    ------------------------------------ ȡ��Ϣ��Ƚ��� -------------------------------------------------
    -----------------------------��ѯ����ϼƿ�ʼ����ɡ����ɡ�֧ȡ��------------------------------------
    v_cur_pickmoney := 0;
    v_cur_monthpay  := 0;
    v_cur_addpay    := 0;
  
    select count(*)
      into v_thisyearsum_num
      from tb_emplydetailacc_stat es
     where es.operationtype in (0, 3, 5, 6)
       and es.happendate between
           to_date(v_interestyear_start, 'yyyy-mm-dd') and
           to_date(v_interestyear_end, 'yyyy-mm-dd')
       and es.emplyacc = v_emplyacc
       and es.unitacc = v_unitacc;
  
    if v_thisyearsum_num > 0 then
      open cr_gjjye_2;
      loop
        fetch cr_gjjye_2
          into v_temp_money, v_biztype;
        exit when cr_gjjye_2%notfound;
      
        if v_biztype = '0' then
          v_cur_monthpay := v_temp_money;
        end if;
        if v_biztype = '3' then
          v_cur_addpay := v_temp_money;
        end if;
        if v_biztype = '5' then
          v_cur_pickmoney := v_cur_pickmoney + v_temp_money;
        end if;
        if v_biztype = '6' then
          v_cur_pickmoney := v_cur_pickmoney + v_temp_money;
        end if;
      
      end loop;
      close cr_gjjye_2;
    end if;
    -----------------------------��ѯ����ϼƽ�������ɡ����ɡ�֧ȡ��------------------------------------
    --------------------------------------���빫��������-----------------------------------------------
    insert into lmk_gjjye gjjye_table
      (gjjye_table.id, --����id
       gjjye_table.balance, --���������
       gjjye_table.emp_name, --ְ������
       gjjye_table.card_num, --֤������
       gjjye_table.org_name, --��λ����
       gjjye_table.last_pay_money, --���һ�νɴ���
       gjjye_table.empid, --ְ���˺�
       gjjye_table.orgid, --��λ�˺�
       gjjye_table.card_kind, --֤������
       gjjye_table.acc_st, --�˻�״̬
       gjjye_table.pre_balance, --�������
       gjjye_table.cur_balance, --�������
       gjjye_table.last_year_balance, --������� 
       gjjye_table.last_year_interest, --������Ϣ  
       gjjye_table.last_pay_emp, --���һ�θ��˲���
       gjjye_table.last_pay_month, --�ϴνɴ�����
       gjjye_table.next_pay_money, --�´νɴ��
       gjjye_table.next_pay_emp, --�´θ��˲���
       gjjye_table.cur_monthpay, --�����ɺϼ� 
       gjjye_table.cur_addpay, --���겹�ɺϼ� 
       gjjye_table.cur_pickmoney, --����֧ȡ�ϼ�  
       gjjye_table.opendate, --��������
       gjjye_table.next_pay_month) --�´νɴ�����
    values
      (seq_lmkgjjye_id.nextval,
       v_accblnce,
       v_emplyname,
       v_emplyid,
       v_unitname,
       v_monthpay,
       v_emplyacc,
       v_unitacc,
       '���֤',
       v_emply_st,
       v_periodorgblnce,
       v_curbalance,
       v_lastbalance,
       v_lastinterest,
       v_emplyhndinamnt,
       v_enddate,
       v_nextpaymoney,
       v_nextemplyhndinamnt,
       v_cur_monthpay,
       v_cur_addpay,
       v_cur_pickmoney,
       v_opendate,
       v_nextpaymonth);
    ------------------------------------������������-----------------------------------------------------
    ------------------------------------��������ϸ��ʼ-----------------------------------------------------
    select count(*)
      into v_gjjmx_num
      from tb_emplydetailacc_stat t_start, tb_hndin_tran h_tran
     where t_start.crdncenum = h_tran.hndincrdncenum(+)
       and t_start.emplyacc = v_emplyacc
       and t_start.unitacc = v_unitacc;
  
    if v_gjjmx_num > 0 then
      open cr_gjjmx;
      loop
        fetch cr_gjjmx
          into v_happendate, v_operationtype, v_loanamnt, v_lendamnt, v_accblnce_mx, v_hndindate;
        exit when cr_gjjmx%notfound;
      
        if v_operationtype = '0' then
          v_operationtype := '�������';
        end if;
        if v_operationtype = '1' then
          v_operationtype := '���Ƿ��';
        end if;
        if v_operationtype = '2' then
          v_operationtype := '���������';
        end if;
        if v_operationtype = '3' then
          v_operationtype := '����';
        end if;
        if v_operationtype = '4' then
          v_operationtype := '����';
        end if;
        if v_operationtype = '5' then
          v_operationtype := '������ȡ';
        end if;
        if v_operationtype = '6' then
          v_operationtype := '������ȡ';
        end if;
        if v_operationtype = '7' then
          v_operationtype := '�ʽ�ת��';
        end if;
        if v_operationtype = '8' then
          v_operationtype := 'ת��ȷ��';
        end if;
        if v_operationtype = '9' then
          v_operationtype := '���˵���';
        end if;
        if v_operationtype = '10' then
          v_operationtype := '���ս�Ϣ';
        end if;
        if v_operationtype = '11' then
          v_operationtype := '�����ת';
        end if;
        if v_operationtype = '12' then
          v_operationtype := '��ת����';
        end if;
      
        v_occurmoney := 0;
        v_cdflag     := '';
      
        if v_loanamnt <> 0 then
          v_occurmoney := v_loanamnt;
          v_cdflag     := '��';
        end if;
        if v_lendamnt <> 0 then
          v_occurmoney := v_lendamnt;
          v_cdflag     := '��';
        end if;
        insert into lmk_gjjmx gjjmx_table
          (gjjmx_table.id, --����id
           gjjmx_table.orgid, --��λ�˺�
           gjjmx_table.org_name, --��λ����
           gjjmx_table.empid, --ְ���˺�
           gjjmx_table.emp_name, --ְ������
           gjjmx_table.sett_date, --����
           gjjmx_table.subtract, --ժҪ
           gjjmx_table.occur_money, --������
           gjjmx_table.balance, --���
           gjjmx_table.credit_debit, --�����ʶ
           gjjmx_table.pre_balance, --�������
           gjjmx_table.pay_month) --�������    
        values
          (seq_lmkgjjmx_id.nextval,
           v_unitacc,
           v_unitname,
           v_emplyacc,
           v_emplyname,
           v_happendate,
           v_operationtype,
           v_occurmoney,
           v_accblnce_mx,
           v_cdflag,
           '',
           v_hndindate);
      end loop;
      close cr_gjjmx;
    end if;
    ------------------------------------��������ϸ����-----------------------------------------------------
    ------------------------------------������ʼ-------------------------------------------------------
    if v_loanaccnum > 0 then
    
      insert into lmk_dkye dkye_table
        (dkye_table.id, --����id
         dkye_table.over_loanmoney, --�������
         dkye_table.emp_name, --ְ������
         dkye_table.card_num, --���֤��
         dkye_table.org_name, --��λ����
         dkye_table.last_back_money, --������
         dkye_table.loan_kou_acc, --�����˺�
         dkye_table.overdue_money, --�������
         dkye_table.loan_money, --�����
         dkye_table.all_back_money, --�ۼƻ����
         dkye_table.over_month, --��������
         dkye_table.remainder_limit, --ʣ������
         dkye_table.next_payday, --���ڿۿ���
         dkye_table.next_back_money) --���ڿۿ���
        select seq_lmkdkye_id.nextval,
               t.unpay_corpus,
               b.borrower_name,
               b.borrower_id,
               b.unitname,
               (select le.rthistermcorpus + le.rthisterminterest
                  from tb_loan_emplydetailacc_stat le
                 where le.iscuroperation = 1
                   and le.contract_code = t.contract_code),
               t.contract_code,
               t.overcorpus,
               t.leave_corpus,
               (select le.rcorpus_total
                  from tb_loan_emplydetailacc_stat le
                 where le.iscuroperation = 1
                   and le.contract_code = t.contract_code),
               t.overmonths,
               t.leave_term,
               '�޹̶��Ŀۿ���',
               t.overcorpus + t.shouldpayinterest + t.thistermcorpus +
               t.thisterminterest
          from tb_loan_info t, tb_borrower_info b
         where t.contract_code = b.contract_code
           and t.contract_code = v_temp_loanacc;
    
    end if;
    ------------------------------------����������-------------------------------------------------------
    ------------------------------------������ϸ��ʼ-------------------------------------------------------
    if v_loanaccnum > 0 then
    
      select count(*)
        into v_dkmx_num
        from tb_loan_emplydetailacc_stat t_loan_start
       where t_loan_start.loan_type != 0
         and t_loan_start.contract_code = v_temp_loanacc
         and t_loan_start.realpay <> 0;
    
      if v_dkmx_num > 0 then
        open cr_dkhsmx;
        loop
          fetch cr_dkhsmx
            into v_operationdate, v_loan_type, v_backcorpus, v_backinterest, v_realpay, v_amnt;
          exit when cr_dkhsmx%notfound;
        
          if v_loan_type = '1' then
            v_loan_type := '���ڻ���';
          end if;
          if v_loan_type = '2' then
            v_loan_type := '��������';
          end if;
          if v_loan_type = '3' then
            v_loan_type := '������ǰ';
          end if;
          if v_loan_type = '4' then
            v_loan_type := 'ȫ����ǰ';
          end if;
        
          insert into lmk_dkhsmx dkhsmx_table
            (dkhsmx_table.id, --����id
             dkhsmx_table.loan_kou_acc, --�����˺� 
             dkhsmx_table.sett_date, --�������� 
             dkhsmx_table.subtract, --ժҪ��ҵ�����ͣ� 
             dkhsmx_table.call_back_coppu, --���ձ��� 
             dkhsmx_table.call_back_interest, --������Ϣ 
             dkhsmx_table.coppu_interest, --��Ϣ�ϼ� 
             dkhsmx_table.real_over_laonmoney) --ʱ�������� 
          
          values
            (seq_lmkdkhsmx_id.nextval,
             v_temp_loanacc,
             v_operationdate,
             v_loan_type,
             v_backcorpus,
             v_backinterest,
             v_realpay,
             v_amnt);
        
        end loop;
        close cr_dkhsmx;
      end if;
    end if;
    ------------------------------------������ϸ����-------------------------------------------------------
  end loop;
  close cr_rq001_empId_orgid;
Exception
  when others then
    RAISE_APPLICATION_ERROR(-20000, '�洢���̳��ִ���', true);
end initializtion;
/
