package spark.template.freemarker.betulozdemir_but;


import java.rmi.Remote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.*;

/* OZKANS FIX */ import spark.template.freemarker.*; /* */

import static spark.Spark.*;

public class Betul_Ozdemir_KullaniciMain/* OZKANS FIX <FreeMarkerRoute, Route> */ {
	static ArrayList<Betul_Ozdemir_VeriTabani> 
	// OZKANS FIX kullaniciList
	/* OZKANS FIX */ kullaniciListe /* */
	= new ArrayList<Betul_Ozdemir_VeriTabani>();
	static Betul_Ozdemir_VeriTabani veriTabani;
	
	/**
	 * Uygulaman_n main fonksiyonu. Uygulama buradan ba_lar..
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		veriTabani = new Betul_Ozdemir_VeriTabani();
		veriTabani.veritabaniHazirla();
		
		// OZKANS FIX }
	    FreeMarkerRoute giris_sayfasi = new FreeMarkerRoute("/betul_ozdemir/giris_sayfasi") {
            public Object handle(Request istek, Response cevap) {
            		return new ModelAndView(null, "/betulozdemir_but/Betul_Ozdemir_giris_sayfasi.html");
            }
        };
     	get(giris_sayfasi);
        
        /* OZKANS FIX Remote */ /* OZKANS FIX */ Route /**/giris_sayfasiPost = new Route("/betul_ozdemir/giris_sayfasi/post") {
            public Object handle(Request istek, Response cevap) {
            		cevap.redirect("/betul_ozdemir/");
            		return null;
            }
        };
        // OZKANS FIX post(girisSayfasiPost);
        /* OZKANS FIX */post(giris_sayfasiPost);
        
        FreeMarkerRoute kullaniciOlusturmaSayfasi = new FreeMarkerRoute("/betul_ozdemir/kullaniciOlusturmaSayfasi") {
            public Object handle(Request istek, Response cevap) {
                return new ModelAndView(null, "/betulozdemir_but/Betul_Ozdemir_kullaniciolusturma.html");
            }
        };
     	get(kullaniciOlusturmaSayfasi);
        
        Route kullaniciOlusturmaSayfasiPost = new Route("/betul_ozdemir/kullanıcıOlusturmaSayfasi/post") {
            public Object handle(Request istek, Response cevap) {
            		String kullaniciAdi = istek.queryParams("kadi");
            		String sifre = istek.queryParams("sifre");
            		String cinsiyet = istek.queryParams("cinsiyet");
            		int yas = Integer.parseInt(istek.queryParams("yas"));
            		
            		System.out.println(kullaniciAdi + sifre + cinsiyet + yas);
            		cevap.redirect("/betul_ozdemir/");
            		return null;
            }
        };
     	post(kullaniciOlusturmaSayfasiPost);
        
        Route kullaniciyiSil = new Route("/iskenderuzuner/kullaniciyi_sil") {
            public Object handle(Request istek, Response cevap) {
            		int id = Integer.parseInt(istek.queryParams("id"));
            		//veritabanindan sil
            		System.out.println(id + "li kullanici silindi...");
            		cevap.redirect("/iskenderuzuner/");
            		return null;
            }
        };
     	post(kullaniciyiSil);
        
        FreeMarkerRoute hataSayfasi = new FreeMarkerRoute("/betul_ozdemir/hata") {
            public Object handle(Request istek, Response cevap) {
                return new ModelAndView(null, "/betulozdemir_but/Betul_Ozdemir_hatasayfasi.html");
            }
        };
     	get(hataSayfasi);
        
        FreeMarkerRoute anaSayfa = new FreeMarkerRoute("/betul_ozdemir/") {
            public Object handle(Request istek, Response cevap) {
            	/* OZKANS FIX */boolean giris_bilgisi = false;
				if(giris_bilgisi == false) {
                		cevap.redirect("/iskenderuzuner/giris_sayfasi");
                		return null;
                }else {
                		/* OZKANS FIX */String kullanici_tipi = "";
						if(kullanici_tipi.equals("normal_kullanici")) {
                			Map<String,Object> ozellikler = new HashMap<>() ;
                    		ozellikler.put("kullanicilar", kullaniciListe);
                    		return new ModelAndView(ozellikler, "/betulozdemir_but/Betul_Ozdemir_adminkullanicianasayfa.html");
                		}else {
                			cevap.redirect("/betul_ozdemir/hata");
                    		return null;
                		}
                }
            }
        };
     	get(anaSayfa);
        
        FreeMarkerRoute adminAnaSayfa = new FreeMarkerRoute("/betul_ozdemir/yonetici") {
            public Object handle(Request istek, Response cevap) {
            		/* OZKANS FIX */ boolean giris_bilgisi = false;
					if(giris_bilgisi == false) {
            			cevap.redirect("/betul_ozdemir/giris_sayfasi");
                		return null;
            		}else {
            			/* OZKANS FIX */ String kullanici_tipi = "";
						if(kullanici_tipi.equals("admin")) {
            				Map<String,Object> ozellikler = new HashMap<>() ;
                    		ozellikler.put("kullanicilar", kullaniciListe);
                    		return new ModelAndView(ozellikler, "/betulozdemir_but/Betul_Ozdemir_adminkullanicianasayfa.html");
                		}else {
                			cevap.redirect("/betul_ozdemir/hata");
                    		return null;
                		}
            		}
            }
        };
        // OZKANS FIX get(adminKullaniciAnaSayfa);
     /* OZKANS FIX */ } /**/
	
	public void kullaniciekle() {
		
	}
	
	public void kullanicisil() {
		
	}
	
	
}