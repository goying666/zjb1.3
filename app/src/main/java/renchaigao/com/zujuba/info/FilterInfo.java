package renchaigao.com.zujuba.info;

public class FilterInfo {
    private Integer id;

    private Integer teamId;

    private String integrityScore;

    private String sexRatio;

    private String careerScreening;

    private String ageScreening;

    private String evaluationScreening;

    private String marriage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getIntegrityScore() {
        return integrityScore;
    }

    public void setIntegrityScore(String integrityScore) {
        this.integrityScore = integrityScore == null ? null : integrityScore.trim();
    }

    public String getSexRatio() {
        return sexRatio;
    }

    public void setSexRatio(String sexRatio) {
        this.sexRatio = sexRatio == null ? null : sexRatio.trim();
    }

    public String getCareerScreening() {
        return careerScreening;
    }

    public void setCareerScreening(String careerScreening) {
        this.careerScreening = careerScreening == null ? null : careerScreening.trim();
    }

    public String getAgeScreening() {
        return ageScreening;
    }

    public void setAgeScreening(String ageScreening) {
        this.ageScreening = ageScreening == null ? null : ageScreening.trim();
    }

    public String getEvaluationScreening() {
        return evaluationScreening;
    }

    public void setEvaluationScreening(String evaluationScreening) {
        this.evaluationScreening = evaluationScreening == null ? null : evaluationScreening.trim();
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage == null ? null : marriage.trim();
    }
}