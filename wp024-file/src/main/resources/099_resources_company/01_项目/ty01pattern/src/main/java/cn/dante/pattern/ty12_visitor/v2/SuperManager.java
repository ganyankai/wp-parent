package cn.dante.pattern.ty12_visitor.v2;

public class SuperManager extends Employee {
    //这类人物的职责非常明确：业绩
    private String skill;

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}