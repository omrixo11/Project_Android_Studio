package com.esprit.lunar;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName="partenaire")
public class Partenaire {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    private int _id;

    @ColumnInfo(name = "Partenaire_titre")
    private String titre;

    @ColumnInfo(name = "nbrPieces")
    private String nbrPieces;



    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }


    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }


    public String getNbrPieces() {
        return nbrPieces;
    }

    public void setNbrPieces(String nbrPieces) {
        this.nbrPieces = nbrPieces;
    }
}
