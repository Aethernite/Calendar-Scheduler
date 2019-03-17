package com.company.View;

public class Exit implements Command{
    private Command parent;

    public Exit(Command parent){
        this.parent = parent;
    }

    @Override
    public Command execute(){
        return null;
    }
}
