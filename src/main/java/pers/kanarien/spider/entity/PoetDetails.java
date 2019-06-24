package com.seewo.spider.entity;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

/**
 * 诗人实体类
 *
 * @author qiuwuhui
 * @date 2019/6/4
 */
@Entity("poet_details")
public class PoetDetails {

    @Id
    private String id;
    private String aid;
    private String xingming;
    private String xingmingpy;
    private String touxiang;
    private String chusheng;
    private String shishi;
    private String zihaobieming;
    private String zi;
    private String hao;
    private String chaodai;
    private String zichaodai;
    private String waihao;
    private String jianjie;
    private String bz;
    private String lianjie;
    private Double paixu;
    private String insertedtime;
    private String updatedtime;
    private String zhbm;
    @Embedded
    private List<Shici> shici;
    @Embedded
    private List<Haoyou> fasong;
    @Embedded
    private List<Haoyou> jieshou;
    @Embedded
    private List<Didian> didian;

    public PoetDetails() {
    }

    public PoetDetails(String id, String xingming) {
        this.id = id;
        this.xingming = xingming;
    }

    public PoetDetails(String id, String aid, String xingming, String xingmingpy, String touxiang, String chusheng,
                       String shishi, String zihaobieming, String zi, String hao, String chaodai, String zichaodai, String waihao,
                       String jianjie, String bz, String lianjie, Double paixu, String insertedtime, String updatedtime,
                       String zhbm) {
        this.id = id;
        this.aid = aid;
        this.xingming = xingming;
        this.xingmingpy = xingmingpy;
        this.touxiang = touxiang;
        this.chusheng = chusheng;
        this.shishi = shishi;
        this.zihaobieming = zihaobieming;
        this.zi = zi;
        this.hao = hao;
        this.chaodai = chaodai;
        this.zichaodai = zichaodai;
        this.waihao = waihao;
        this.jianjie = jianjie;
        this.bz = bz;
        this.lianjie = lianjie;
        this.paixu = paixu;
        this.insertedtime = insertedtime;
        this.updatedtime = updatedtime;
        this.zhbm = zhbm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getXingming() {
        return xingming;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
    }

    public String getXingmingpy() {
        return xingmingpy;
    }

    public void setXingmingpy(String xingmingpy) {
        this.xingmingpy = xingmingpy;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang;
    }

    public String getChusheng() {
        return chusheng;
    }

    public void setChusheng(String chusheng) {
        this.chusheng = chusheng;
    }

    public String getShishi() {
        return shishi;
    }

    public void setShishi(String shishi) {
        this.shishi = shishi;
    }

    public String getZihaobieming() {
        return zihaobieming;
    }

    public void setZihaobieming(String zihaobieming) {
        this.zihaobieming = zihaobieming;
    }

    public String getZi() {
        return zi;
    }

    public void setZi(String zi) {
        this.zi = zi;
    }

    public String getHao() {
        return hao;
    }

    public void setHao(String hao) {
        this.hao = hao;
    }

    public String getChaodai() {
        return chaodai;
    }

    public void setChaodai(String chaodai) {
        this.chaodai = chaodai;
    }

    public String getZichaodai() {
        return zichaodai;
    }

    public void setZichaodai(String zichaodai) {
        this.zichaodai = zichaodai;
    }

    public String getWaihao() {
        return waihao;
    }

    public void setWaihao(String waihao) {
        this.waihao = waihao;
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getLianjie() {
        return lianjie;
    }

    public void setLianjie(String lianjie) {
        this.lianjie = lianjie;
    }

    public Double getPaixu() {
        return paixu;
    }

    public void setPaixu(Double paixu) {
        this.paixu = paixu;
    }

    public String getInsertedtime() {
        return insertedtime;
    }

    public void setInsertedtime(String insertedtime) {
        this.insertedtime = insertedtime;
    }

    public String getUpdatedtime() {
        return updatedtime;
    }

    public void setUpdatedtime(String updatedtime) {
        this.updatedtime = updatedtime;
    }

    public String getZhbm() {
        return zhbm;
    }

    public void setZhbm(String zhbm) {
        this.zhbm = zhbm;
    }

    public List<Shici> getShici() {
        return shici;
    }

    public void setShici(List<Shici> shici) {
        this.shici = shici;
    }

    public List<Haoyou> getFasong() {
        return fasong;
    }

    public void setFasong(List<Haoyou> fasong) {
        this.fasong = fasong;
    }

    public List<Haoyou> getJieshou() {
        return jieshou;
    }

    public void setJieshou(List<Haoyou> jieshou) {
        this.jieshou = jieshou;
    }

    public List<Didian> getDidian() {
        return didian;
    }

    public void setDidian(List<Didian> didian) {
        this.didian = didian;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PoetDetails{");
        sb.append("id='").append(id).append('\'');
        sb.append(", aid='").append(aid).append('\'');
        sb.append(", xingming='").append(xingming).append('\'');
        sb.append(", xingmingpy='").append(xingmingpy).append('\'');
        sb.append(", touxiang='").append(touxiang).append('\'');
        sb.append(", chusheng='").append(chusheng).append('\'');
        sb.append(", shishi='").append(shishi).append('\'');
        sb.append(", zihaobieming='").append(zihaobieming).append('\'');
        sb.append(", zi='").append(zi).append('\'');
        sb.append(", hao='").append(hao).append('\'');
        sb.append(", chaodai='").append(chaodai).append('\'');
        sb.append(", zichaodai='").append(zichaodai).append('\'');
        sb.append(", waihao='").append(waihao).append('\'');
        sb.append(", jianjie='").append(jianjie).append('\'');
        sb.append(", bz='").append(bz).append('\'');
        sb.append(", lianjie='").append(lianjie).append('\'');
        sb.append(", paixu=").append(paixu);
        sb.append(", insertedtime='").append(insertedtime).append('\'');
        sb.append(", updatedtime='").append(updatedtime).append('\'');
        sb.append(", zhbm='").append(zhbm).append('\'');
        sb.append(", shici=").append(shici);
        sb.append(", fasong=").append(fasong);
        sb.append(", jieshou=").append(jieshou);
        sb.append(", didian=").append(didian);
        sb.append('}');
        return sb.toString();
    }

    public static class Shici {
        private String id;
        private String biaoti;
        private String neirong;

        public Shici() {
        }

        public Shici(String id, String biaoti, String neirong) {
            this.id = id;
            this.biaoti = biaoti;
            this.neirong = neirong;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBiaoti() {
            return biaoti;
        }

        public void setBiaoti(String biaoti) {
            this.biaoti = biaoti;
        }

        public String getNeirong() {
            return neirong;
        }

        public void setNeirong(String neirong) {
            this.neirong = neirong;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Shici{");
            sb.append("id='").append(id).append('\'');
            sb.append(", biaoti='").append(biaoti).append('\'');
            sb.append(", neirong='").append(neirong).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    public static class Haoyou {
        private String id;
        private String xm;
        private Double pc;

        public Haoyou() {
        }

        public Haoyou(String id, String xm, Double pc) {
            this.id = id;
            this.xm = xm;
            this.pc = pc;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getXm() {
            return xm;
        }

        public void setXm(String xm) {
            this.xm = xm;
        }

        public Double getPc() {
            return pc;
        }

        public void setPc(Double pc) {
            this.pc = pc;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Haoyou{");
            sb.append("id='").append(id).append('\'');
            sb.append(", xm='").append(xm).append('\'');
            sb.append(", pc=").append(pc);
            sb.append('}');
            return sb.toString();
        }
    }

    public static class Didian {
        private String didian;
        private Integer scs;

        public Didian() {
        }

        public Didian(String didian, Integer scs) {
            this.didian = didian;
            this.scs = scs;
        }

        public String getDidian() {
            return didian;
        }

        public void setDidian(String didian) {
            this.didian = didian;
        }

        public Integer getScs() {
            return scs;
        }

        public void setScs(Integer scs) {
            this.scs = scs;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("didian{");
            sb.append("didian='").append(didian).append('\'');
            sb.append(", scs=").append(scs);
            sb.append('}');
            return sb.toString();
        }
    }
}
