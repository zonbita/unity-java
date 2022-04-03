using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Ontrigger : MonoBehaviour
{
    // Start is called before the first frame update
    public bool active = false;
    private BoxCollider2D boxCollider2D;
    public GameObject gameObject;
    Rigidbody2D rigidbody2D;
    void Start()
    {
        boxCollider2D = this.GetComponentInParent<BoxCollider2D>();
    }
    void OnTriggerEnter2D(Collider2D collision){
        if (collision.tag == "Player"){
           active = true;
           //rigidbody2D = gameObject.AddComponent<Rigidbody2D>()  as Rigidbody2D;
        }
    }

    void OnTriggerStay2D(Collider2D collision)
    {
        if(collision.gameObject.tag=="Player"){
            active = true;
        }
    }
 
    void OnTriggerExit2D(Collider2D collision)
    {   
       active = false;
    }
}
