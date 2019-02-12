
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author i14053
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Masukan banyak pinjaman: ");
        double banyakPinjaman=sc.nextInt();
       
        System.out.println("Masukan bunga pinjaman: ");
        double bunga=sc.nextDouble()/100;
        
        System.out.println("Masukan lama peminjaman(Dalam per bulan): ");
        double lamaPinjaman=sc.nextInt();
        
        System.out.println("Masukan biaya administrasi: ");
        double biayaAdmin=sc.nextDouble();
        
        Simulasi sim=new Simulasi(banyakPinjaman,bunga,lamaPinjaman);
        sim.cicilanPokok(banyakPinjaman, lamaPinjaman);
        
        DecimalFormat df=(DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols rp=new DecimalFormatSymbols();
        rp.setCurrencySymbol("Rp. ");
        rp.setMonetaryDecimalSeparator(',');
        rp.setGroupingSeparator('.');
        df.setDecimalFormatSymbols(rp);
        
        double totalBunga = 0;
        double totalPokok = 0;
        double totalCicilan = 0;
        double totalBiayaAdmin=0;
        
        for (int i = 1; i <= lamaPinjaman; i++) {
            double bpb=sim.bungaPerBulan(i,banyakPinjaman,bunga, lamaPinjaman);
            double cbk=sim.cicilanBungaKe(i,banyakPinjaman, bunga, lamaPinjaman);
            double jap=sim.jumlahAngsuranPokok(banyakPinjaman, lamaPinjaman);
            System.out.println("");
            System.out.printf("Bunga bulan ke-"+i+" "+df.format(bpb));
            System.out.println("");
            System.out.printf("Cicilan bulan ke-"+i+" "+df.format(cbk));
            System.out.println("");
            System.out.println("");
            totalBunga=totalBunga+bpb;
            totalCicilan=totalCicilan+cbk+biayaAdmin;
            totalPokok=totalPokok+jap;
            totalBiayaAdmin=totalBiayaAdmin+biayaAdmin;
        }
        
        System.out.printf("Total bunga: "+df.format(totalBunga));
        System.out.println("");
        System.out.printf("Total angsuran: "+df.format(totalCicilan));
        System.out.println("");
        System.out.printf("Total angsuran pokok: "+df.format(totalPokok));
        System.out.println("");
        System.out.printf("Total biaya admin: "+df.format(totalBiayaAdmin));
    }
}
class Simulasi{
    double pinjaman;
    double bunga;
    double lamaPeminjaman;
    
    public Simulasi(double pnj,double bng,double lp){
        this.bunga=bng;
        this.pinjaman=pnj;
        this.lamaPeminjaman=lp;
    }
    public double cicilanPokok(double pnj,double lp){
        return pnj/lp;
    }
    public double bungaPerBulan(double bke,double pnj,double bng,double lp){
        return (pnj-((bke-1)*cicilanPokok(pnj,lp)))*bng/12;
    }
    public double cicilanBungaKe(double bke,double pnj,double bng,double lp){
        return bungaPerBulan(bke,pnj,bng,lp)+cicilanPokok(pnj,lp);
    }
    public double jumlahAngsuranPokok(double pnj,double lp){
        return pnj/lp;
    }
}