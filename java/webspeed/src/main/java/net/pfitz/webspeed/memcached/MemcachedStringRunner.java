package net.pfitz.webspeed.memcached;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import net.pfitz.webspeed.Person;
import net.pfitz.webspeed.Runner;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.transcoders.Transcoder;

public class MemcachedStringRunner extends Runner {
	private MemcachedClient client = null;
	private String content = "'omkib]ss`flergjkslrhlkZjmidcl_[^rZcfss]fjZh[e^s_oobho[``][ok`fimg^_hhhmia[c[nh]d^c]Zcob`pZe[dp_aZgjZernsmknqadkokr^`fnhgd[skrspdZrbeafj]mkjkZhgke]pcccjdhoqZkeZ`mcjeglnooncdj]qdhcZfogoghm^fi^q_`ibng_lb^acr[o][licjmc[`g`apmncZZn_eZr[rjZ]]rrrcdbcgnn_r]qsqj^mqohfs^poi[]r]^f_m`qrrsrsmksehqcqarhdcie]bgfjmis`hj`qcrciheqghl`a^q]jorio`slqkhi]bg`l[[bdgaegffoqlikisgan`prdkbf[njfZm^hbodke^rqe_llddqmcf]ke^r^hdigropse]_^anfa^boa`fbi`gffporfkqlq[jhhdbcdnh]pbdfZinjildk^_`high_h]gsbkrd_cZimrfrge]cr`q]d[lsj`o_^`rro^siemZ^gilnd^csZ`cplp_flgaf[o_bo]jc]lhofpqdmjpm`bm[^smoprqk]nZm[^m[gaoZZieen`ao`n[baqp]cegkhhr`jan[hko^]kclkrsr]cm_bp_`lnb_qlkd_fnabeed_]i^smlseljg[b_en^fr_]Zmmlkcl_onalkfaacqhi`hmrepd_sj[ffrc^qkohrdZqfspmZ]kpoqqhfscss`qigdh_jjiioc]dfhsZd]cda^]hdsgfg_akbdaonb[fihjk[e_d[d[iq`mi^ogn`_bq[onplkehcsonnjlkpildo`ampmjkroqmoqkjqra^hbbrolbg_nZebg[e^Zmq_lmc]cifqnjjof_Z^orqm^g`Zlh_Z`]eih^q^gsfdm_gph[c]e`lkn`csZ]kk]chql_[d]pm_s^^[_gifqidilde[nj[jj__kckl_qe^i[`Zpjed'";
	
	public MemcachedStringRunner() {
		try 
		{
			String path = "../data/records.json";
			ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally					
			this.client = new MemcachedClient(
						new InetSocketAddress("127.0.0.1", 11211));
		} catch (Exception e) 
		{
			throw new RuntimeException(e);
		}		
	}
	
	public void runIteration()
	{
		String key = "thisisamemcachedkey";
		this.client.set(key, 1000, this.content);
		String retrieved = (String)this.client.get(key);		
		assert retrieved.length() == this.content.length();
	}
}

