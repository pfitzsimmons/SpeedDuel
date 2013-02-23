import json
import os
import pylibmc

data_path = os.path.join(os.path.dirname(__file__), '../data')


class Runner(object):
    def __init__(self):
        f = open(data_path + '/records.json')
        self.records = json.load(f)
        f.close()
        self.client = pylibmc.Client(
            ["127.0.0.1"], 
            binary=True,
            behaviors={"tcp_nodelay": True,
                       "ketama": True})

    def do_once(self):
        key = 'mytestcachekey'
        self.client.set(key, self.records)
        retrieved = self.client.get(key)
        assert len(retrieved) == len(self.records)

    def do_many(self, num):
        key = 'mytestcachekey'
        for x in xrange(0, num):
            self.client.set(key, self.records)
            retrieved = self.client.get(key)
            assert len(retrieved) == len(self.records)
            

class StringRunner(object):
    def __init__(self):
        self.content = "'omkib]ss`flergjkslrhlkZjmidcl_[^rZcfss]fjZh[e^s_oobho[``][ok`fimg^_hhhmia[c[nh]d^c]Zcob`pZe[dp_aZgjZernsmknqadkokr^`fnhgd[skrspdZrbeafj]mkjkZhgke]pcccjdhoqZkeZ`mcjeglnooncdj]qdhcZfogoghm^fi^q_`ibng_lb^acr[o][licjmc[`g`apmncZZn_eZr[rjZ]]rrrcdbcgnn_r]qsqj^mqohfs^poi[]r]^f_m`qrrsrsmksehqcqarhdcie]bgfjmis`hj`qcrciheqghl`a^q]jorio`slqkhi]bg`l[[bdgaegffoqlikisgan`prdkbf[njfZm^hbodke^rqe_llddqmcf]ke^r^hdigropse]_^anfa^boa`fbi`gffporfkqlq[jhhdbcdnh]pbdfZinjildk^_`high_h]gsbkrd_cZimrfrge]cr`q]d[lsj`o_^`rro^siemZ^gilnd^csZ`cplp_flgaf[o_bo]jc]lhofpqdmjpm`bm[^smoprqk]nZm[^m[gaoZZieen`ao`n[baqp]cegkhhr`jan[hko^]kclkrsr]cm_bp_`lnb_qlkd_fnabeed_]i^smlseljg[b_en^fr_]Zmmlkcl_onalkfaacqhi`hmrepd_sj[ffrc^qkohrdZqfspmZ]kpoqqhfscss`qigdh_jjiioc]dfhsZd]cda^]hdsgfg_akbdaonb[fihjk[e_d[d[iq`mi^ogn`_bq[onplkehcsonnjlkpildo`ampmjkroqmoqkjqra^hbbrolbg_nZebg[e^Zmq_lmc]cifqnjjof_Z^orqm^g`Zlh_Z`]eih^q^gsfdm_gph[c]e`lkn`csZ]kk]chql_[d]pm_s^^[_gifqidilde[nj[jj__kckl_qe^i[`Zpjed'"
        self.client = pylibmc.Client(
            ["127.0.0.1"], 
            binary=True,
            behaviors={"tcp_nodelay": True,
                       "ketama": True})

    def do_once(self):
        key = 'mytestcachekey'
        self.client.set(key, self.content)
        retrieved = self.client.get(key)
        assert len(retrieved) == len(self.content)
            
