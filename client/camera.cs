using UnityEngine;

public class camera : MonoBehaviour
{
    public Vector3 offset;
    public Transform target;
    [Range(1,10)]
    public float smoothFactor;
    private Vector3 velocity = Vector3.zero;

    private void FixedUpdate()
    {
       Follow();
    }
    void Follow(){
        Vector3 targetPosition = offset + target.position;
        Vector3 smoothPosition = Vector3.SmoothDamp(transform.position, targetPosition, ref velocity, smoothFactor);
        transform.position = smoothPosition;
        
    }
}
