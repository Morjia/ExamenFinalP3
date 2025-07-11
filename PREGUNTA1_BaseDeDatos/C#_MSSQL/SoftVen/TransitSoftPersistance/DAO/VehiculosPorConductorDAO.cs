using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftVenModel;

namespace SoftVenPersistance.DAO
{
    public interface VehiculosPorConductorDAO
    {
        int Insertar(VehiculosPorConductorDTO vehiculoPorConductor);
        VehiculosPorConductorDTO ObtenerPorId(int vehiculoId, int conductorId);
        BindingList<VehiculosPorConductorDTO> ListarTodos();
        int Modificar(VehiculosPorConductorDTO vehiculoPorConductor);
        int Eliminar(VehiculosPorConductorDTO vehiculoPorConductor);
    }
}
