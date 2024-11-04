using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Task330.Publisher.Models;

[Table("tbl_Creator")]
public class Creator : BaseModel
{
    [Required]
    [MinLength(2)]
    [MaxLength(64)]
    [Column(TypeName = "text")]
    public string Firstname { get; set; }

    [Required]
    [MinLength(2)]
    [MaxLength(64)]
    [Column(TypeName = "text")]
    public string Lastname { get; set; }

    [Required]
    [MinLength(2)]
    [MaxLength(64)]
    [Column(TypeName = "text")]
    public string Login { get; set; }

    [Required]
    [MinLength(8)]
    [MaxLength(128)]
    [Column(TypeName = "text")]
    public string Password { get; set; }

    public virtual ICollection<News> News { get; set; } = [];
}