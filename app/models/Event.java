package models;

import play.data.validation.*;
import play.db.jpa.Model;
import play.libs.Codec;

import javax.persistence.*;
import java.util.*;

@Entity
public class Event extends Model {
    @Required public String title;
    @Required public String author;
    @ManyToOne public User user;
    @Lob @Required @MaxSize(10000) public String content;
}