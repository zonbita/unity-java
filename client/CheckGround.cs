using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CheckGround : MonoBehaviour
{
    BoxCollider2D boxCollider2D;
    move m;
    // Start is called before the first frame update
    void Start()
    {
        m = this.GetComponentInParent<move>();
        boxCollider2D = this.GetComponentInParent<BoxCollider2D>();
    }

    void OnTriggerEnter2D(Collider2D collision){
        if (collision.tag == "Ground"){
           // m.ground =  true;
        }
    }

    void OnTriggerStay2D(Collider2D collision)
    {
        if(collision.gameObject.tag=="Ground"){
            m.ground = true;
        }
    }
 
    void OnTriggerExit2D(Collider2D collision)
    {   
        m.ground = false;
    }
}
