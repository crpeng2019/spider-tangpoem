package com.seewo.spider.entity;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * 诗歌实体类
 *
 * @author qiuwuhui
 * @date 2019/6/5
 */
@Entity("poem_details")
public class PoemDetails {

    @Id
    /** 诗歌id */
    private String id;
    /** 标题 */
    private String title;
    /** 作者 */
    private String author;
    /** 内容 */
    private String content;
    /** 注解 */
    private String annotations;
    /** 赏析 */
    private String analysis;
    /** 翻译 */
    private String translation;
    @Embedded
    /** InfoBox */
    private InfoBox infoBox;

    public PoemDetails() {
    }

    public PoemDetails(String id, String title, String author, String content, String annotations, String analysis, String translation, InfoBox infoBox) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.annotations = annotations;
        this.analysis = analysis;
        this.translation = translation;
        this.infoBox = infoBox;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public InfoBox getInfoBox() {
        return infoBox;
    }

    public void setInfoBox(InfoBox infoBox) {
        this.infoBox = infoBox;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PoemDetails{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", annotations='").append(annotations).append('\'');
        sb.append(", analysis='").append(analysis).append('\'');
        sb.append(", translation='").append(translation).append('\'');
        sb.append(", infoBox=").append(infoBox);
        sb.append('}');
        return sb.toString();
    }

    public static class InfoBox {
        /** 风格 */
        private String style;
        /** 教材 */
        private String bookName;
        /** 卷ID */
        private String volumeId;
        /** 编年 */
        private String annals;
        /** 题材 */
        private String theme;
        /** 体制 */
        private String form;
        /** 功能 */
        private String function;
        /** 语言 */
        private String intonation;
        /** 情感 */
        private String emotion;
        /** 学习阶段 */
        private String learningPhase;
        /** 标签 */
        private String label;

        public InfoBox() {
        }

        public InfoBox(String style, String bookName, String volumeId, String annals, String theme, String form,
                       String function, String intonation, String emotion, String learningPhase, String label) {
            this.style = style;
            this.bookName = bookName;
            this.volumeId = volumeId;
            this.annals = annals;
            this.theme = theme;
            this.form = form;
            this.function = function;
            this.intonation = intonation;
            this.emotion = emotion;
            this.learningPhase = learningPhase;
            this.label = label;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getVolumeId() {
            return volumeId;
        }

        public void setVolumeId(String volumeId) {
            this.volumeId = volumeId;
        }

        public String getAnnals() {
            return annals;
        }

        public void setAnnals(String annals) {
            this.annals = annals;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public String getForm() {
            return form;
        }

        public void setForm(String form) {
            this.form = form;
        }

        public String getFunction() {
            return function;
        }

        public void setFunction(String function) {
            this.function = function;
        }

        public String getIntonation() {
            return intonation;
        }

        public void setIntonation(String intonation) {
            this.intonation = intonation;
        }

        public String getEmotion() {
            return emotion;
        }

        public void setEmotion(String emotion) {
            this.emotion = emotion;
        }

        public String getLearningPhase() {
            return learningPhase;
        }

        public void setLearningPhase(String learningPhase) {
            this.learningPhase = learningPhase;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("InfoBox{");
            sb.append("style='").append(style).append('\'');
            sb.append(", bookName='").append(bookName).append('\'');
            sb.append(", volumeId='").append(volumeId).append('\'');
            sb.append(", annals='").append(annals).append('\'');
            sb.append(", theme='").append(theme).append('\'');
            sb.append(", form='").append(form).append('\'');
            sb.append(", function='").append(function).append('\'');
            sb.append(", intonation='").append(intonation).append('\'');
            sb.append(", emotion='").append(emotion).append('\'');
            sb.append(", learningPhase='").append(learningPhase).append('\'');
            sb.append(", label='").append(label).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
