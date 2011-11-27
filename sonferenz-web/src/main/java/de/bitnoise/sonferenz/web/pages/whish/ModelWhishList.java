package de.bitnoise.sonferenz.web.pages.whish;

import java.io.Serializable;

import de.bitnoise.sonferenz.model.WhishModel;

public class ModelWhishList implements Serializable 
{
    public String title;
    public String owner;
    public String description;
    public WhishModel whish;
    public Integer like;
    public Integer sumLike;
    public Integer  id;

}
